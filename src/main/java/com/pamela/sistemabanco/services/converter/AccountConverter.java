package com.pamela.sistemabanco.services.converter;

import com.pamela.sistemabanco.models.Account;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceRequest;

public class AccountConverter {

    public static Account accountRequestToAccount(final AccountServiceRequest accountRequest) {
       return Account.builder()
               .accountNumber(accountRequest.getAccountNumber())
               .build();
    }
}
