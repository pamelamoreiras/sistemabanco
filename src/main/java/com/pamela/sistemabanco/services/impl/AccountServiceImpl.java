package com.pamela.sistemabanco.services.impl;

import com.pamela.sistemabanco.models.Account;
import com.pamela.sistemabanco.models.TransactionHistory;
import com.pamela.sistemabanco.repositories.AccountRepository;
import com.pamela.sistemabanco.repositories.ClientRepository;
import com.pamela.sistemabanco.repositories.TransactionHistoryRepository;
import com.pamela.sistemabanco.services.AccountService;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceRequest;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceResponse;
import com.pamela.sistemabanco.services.dtos.account.HistoryResponse;
import com.pamela.sistemabanco.services.dtos.account.TransactionRequest;
import com.pamela.sistemabanco.services.exceptions.account.AccountAlreadyExistsException;
import com.pamela.sistemabanco.services.exceptions.account.AccountNotFoundException;
import com.pamela.sistemabanco.services.exceptions.account.InsufficientFundsException;
import com.pamela.sistemabanco.services.exceptions.account.MaximumNumberOfAccountsCreatedException;
import com.pamela.sistemabanco.services.exceptions.client.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final TransactionHistoryRepository historyRepository;

    @Override
    public AccountServiceResponse createAccount(final AccountServiceRequest request) {

        final var client = clientRepository.findByDocument(request.getDocument());

        final var account = accountRepository.findAccountByAccountNumber(request.getAccountNumber());

        if (client == null) {
            throw new ClientNotFoundException("Cliente não encontrado");
        }

        if (account == null) {

            List<Account> accounts = new ArrayList<>(client.getAccounts());

            final var createdAccount = Account.builder()
                    .accountNumber(request.getAccountNumber())
                    .balance(BigDecimal.ZERO)
                    .build();

            if (accounts.size() < 2) {
                accounts.add(createdAccount);
                client.setAccounts(accounts);

            } else {
                throw new MaximumNumberOfAccountsCreatedException("Cliente já possui número máximo de contas.");
            }

            this.clientRepository.save(client);

            return AccountServiceResponse.builder()
                    .name(client.getName())
                    .accountNumber(createdAccount.getAccountNumber())
                    .document(client.getDocument())
                    .balance(createdAccount.getBalance())
                    .build();
        } else {
            throw new AccountAlreadyExistsException("Conta já existente");
        }
    }

    @Override
    @Transactional
    public void deleteAccount(final Long accountNumber) {

        accountRepository.deleteByAccountNumber(accountNumber);
    }

    @Override
    public void deposit(final TransactionRequest depositRequest) {

        final Account account = getAccountByNumber(depositRequest);

        if (account == null) {
            throw new AccountNotFoundException("Conta não encontrada");
        }

        account.setBalance(account.getBalance().add(depositRequest.getAmount()));

        accountRepository.save(account);

        historyRepository.save(TransactionHistory.builder()
                .account(account)
                .amount(depositRequest.getAmount())
                .timestamp(LocalDateTime.now())
                .build());
    }

    @Override
    public void withdraw(final TransactionRequest withdrawRequest) {
        final Account account = getAccountByNumber( withdrawRequest);

        if (account == null) {
            throw new AccountNotFoundException("Account não encontrada");
        }

        if (account.getBalance().compareTo(withdrawRequest.getAmount()) >= 0) {

            account.setBalance(account.getBalance().subtract(withdrawRequest.getAmount()));

            accountRepository.save(account);

            historyRepository.save(TransactionHistory.builder()
                    .account(account)
                    .amount(withdrawRequest.getAmount().negate())
                    .timestamp(LocalDateTime.now())
                    .build());
        } else {
            throw new InsufficientFundsException("Saldo Insuficiente");
        }
    }

    @Override
    public List<HistoryResponse> getHistoryByAccountId(final Long accountId) {
        final List<TransactionHistory> transactionHistory = historyRepository.getAllTransactionHistoryByAccountId(accountId);

        return transactionHistory.stream()
                .map(element -> HistoryResponse.builder()
                        .amount(element.getAmount())
                        .date(element.getTimestamp())
                        .build()).collect(Collectors.toList());
    }


    private Account getAccountByNumber(final TransactionRequest depositRequest) {
        return this.accountRepository.findAccountByAccountNumber(depositRequest.getAccountNumber());
    }
}
