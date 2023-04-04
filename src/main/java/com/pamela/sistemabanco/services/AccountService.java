package com.pamela.sistemabanco.services;

import com.pamela.sistemabanco.services.dtos.account.AccountServiceRequest;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceResponse;
import com.pamela.sistemabanco.services.dtos.account.HistoryResponse;
import com.pamela.sistemabanco.services.dtos.account.TransactionRequest;

import java.util.List;

public interface AccountService {

    AccountServiceResponse createAccount(final AccountServiceRequest accountServiceRequest);

    void deleteAccount(final Long accountNumber);

    void deposit(final TransactionRequest depositRequest);

    void withdraw(final TransactionRequest withdrawRequest);

    List<HistoryResponse> getHistoryByAccountId(final Long accountId);
}
