package com.pamela.sistemabanco.services.converter;

import com.pamela.sistemabanco.models.Account;
import com.pamela.sistemabanco.services.dtos.account.AccountToClientDetailsService;

public class AccountConverter {

    public static AccountToClientDetailsService accountRequestToAccountResponse(final Account accountRequest) {
       return AccountToClientDetailsService.builder()
               .id(accountRequest.getId())
               .accountNumber(accountRequest.getAccountNumber())
               .balance(accountRequest.getBalance())
               .build();
    }
}
