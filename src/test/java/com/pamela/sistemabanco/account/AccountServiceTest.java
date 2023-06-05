package com.pamela.sistemabanco.account;

import com.pamela.sistemabanco.models.Account;
import com.pamela.sistemabanco.models.Client;
import com.pamela.sistemabanco.models.TransactionHistory;
import com.pamela.sistemabanco.repositories.AccountRepository;
import com.pamela.sistemabanco.repositories.ClientRepository;
import com.pamela.sistemabanco.repositories.TransactionHistoryRepository;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceRequest;
import com.pamela.sistemabanco.services.dtos.account.TransactionRequest;
import com.pamela.sistemabanco.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TransactionHistoryRepository historyRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void whenClientAndNewAccountIsInformedThenItShouldBeCreated() {
        final var accountRequest = AccountServiceRequest.builder()
                .document("14496107651")
                .accountNumber(2409L)
                .build();

        final var expectAccount = Account.builder()
                .id(1L)
                .accountNumber(2409L)
                .transactionHistory(List.of(TransactionHistory.builder().build()))
                .balance(BigDecimal.ZERO)
                .build();

        Mockito.when(clientRepository.findByDocument(accountRequest.getDocument())).thenReturn(Client.builder().accounts(List.of(Account.builder().accountNumber(233L).balance(
                BigDecimal.ZERO

        ).build())).build());

        final var actualResult = accountService.createAccount(accountRequest);

        Assertions.assertNotNull(actualResult);
        Assertions.assertEquals(expectAccount.getAccountNumber(), actualResult.getAccountNumber());
    }

    @Test
    void whenValidClientDocumentIsGivenThenItShouldBeDeleted() {

        final var accountRequest = AccountServiceRequest.builder()
                .document("14496107651")
                .accountNumber(2409L)
                .build();

        Mockito.doNothing().when(accountRepository).deleteById(accountRequest.getAccountNumber());

        accountService.deleteAccount(accountRequest.getAccountNumber());

        Mockito.verify(accountRepository,Mockito.times(1)).deleteById(accountRequest.getAccountNumber());
    }

    @Test
    void whenValidAccountIsInformedItShouldBeDeposited() {

        final var expectAccount = Account.builder()
                .id(1L)
                .accountNumber(2409L)
                .transactionHistory(List.of(TransactionHistory.builder().build()))
                .balance(BigDecimal.ZERO)
                .build();

        Mockito.when(accountRepository.findAccountByAccountNumber(expectAccount.getAccountNumber())).thenReturn(expectAccount);

        accountService.deposit(TransactionRequest.builder()
                        .accountNumber(expectAccount.getAccountNumber())
                        .amount(BigDecimal.TEN)
                .build());
    }

    @Test
    void whenValidAccountIsInformedItShouldBeWithdraw() {

        final var expectAccount = Account.builder()
                .id(1L)
                .accountNumber(2409L)
                .transactionHistory(List.of(TransactionHistory.builder().build()))
                .balance(BigDecimal.TEN)
                .build();

        Mockito.when(accountRepository.findAccountByAccountNumber(expectAccount.getAccountNumber())).thenReturn(expectAccount);

        accountService.withdraw(TransactionRequest.builder()
                .accountNumber(expectAccount.getAccountNumber())
                .amount(BigDecimal.ONE)
                .build());
    }

    @Test
    void  whenListHistoryAccountIsCalledThenItShouldBeReturned() {

        final var expectHistoryAccount = TransactionHistory.builder()
                .account(Account.builder().id(1L).build())
                .timestamp(LocalDateTime.now())
                .amount(BigDecimal.TEN)
                .build();

        Mockito.when(historyRepository.getAllTransactionHistoryByAccountId(expectHistoryAccount.getAccount().getId())).thenReturn(List.of(expectHistoryAccount));

        final var actualTransaction = accountService.getHistoryByAccountId(expectHistoryAccount.getAccount().getId());

        Assertions.assertNotNull(actualTransaction);
    }
}
