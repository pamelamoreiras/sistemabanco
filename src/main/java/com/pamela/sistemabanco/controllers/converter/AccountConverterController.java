package com.pamela.sistemabanco.controllers.converter;

import com.pamela.sistemabanco.controllers.dtos.account.AccountControllerRequest;
import com.pamela.sistemabanco.controllers.dtos.account.AccountControllerResponse;
import com.pamela.sistemabanco.controllers.dtos.account.AccountToClientDetailsController;
import com.pamela.sistemabanco.controllers.dtos.account.HistoryControllerResponse;
import com.pamela.sistemabanco.controllers.dtos.account.TransactionControllerRequest;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceRequest;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceResponse;
import com.pamela.sistemabanco.services.dtos.account.AccountToClientDetailsService;
import com.pamela.sistemabanco.services.dtos.account.HistoryResponse;
import com.pamela.sistemabanco.services.dtos.account.TransactionRequest;

public class AccountConverterController {

    public static AccountServiceRequest accountControllerToAccountRequest(final AccountControllerRequest accountControllerRequest) {
        return AccountServiceRequest.builder()
                .document(accountControllerRequest.getDocument())
                .accountNumber(accountControllerRequest.getAccountNumber())
                .build();
    }

    public static AccountControllerResponse accountServiceResponseToAccountControllerResponse(final AccountServiceResponse accountServiceResponse) {
        return AccountControllerResponse.builder()
                .id(accountServiceResponse.getId())
                .name(accountServiceResponse.getName())
                .document(accountServiceResponse.getDocument())
                .accountNumber(accountServiceResponse.getAccountNumber())
                .balance(accountServiceResponse.getBalance())
                .build();
    }

    public static AccountToClientDetailsController accountToClientDetailsServiceToAccountDetailsControllerResponse(final AccountToClientDetailsService accountServiceResponse) {
        return AccountToClientDetailsController.builder()
                .id(accountServiceResponse.getId())
                .accountNumber(accountServiceResponse.getAccountNumber())
                .balance(accountServiceResponse.getBalance())
                .build();
    }

    public static TransactionRequest transactionControllerRequestToTransactionRequest(final TransactionControllerRequest transactionRequest) {
        return TransactionRequest.builder()
                .amount(transactionRequest.getAmount())
                .accountNumber(transactionRequest.getAccountNumber())
                .build();
    }

    public static HistoryControllerResponse historyResponseToHistoryControllerResponse(final HistoryResponse historyResponse) {
        return HistoryControllerResponse.builder()
                .id(historyResponse.getId())
                .date(historyResponse.getDate())
                .amount(historyResponse.getAmount())
                .build();
    }
}
