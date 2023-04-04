package com.pamela.sistemabanco.controllers.converter;

import com.pamela.sistemabanco.controllers.dtos.account.AccountControllerRequest;
import com.pamela.sistemabanco.controllers.dtos.account.AccountControllerResponse;
import com.pamela.sistemabanco.controllers.dtos.account.HistoryControllerResponse;
import com.pamela.sistemabanco.controllers.dtos.account.TransactionControllerRequest;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceRequest;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceResponse;
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
                .name(accountServiceResponse.getName())
                .document(accountServiceResponse.getDocument())
                .numberAccount(accountServiceResponse.getNumberAccount())
                .balance(accountServiceResponse.getBalance())
                .build();
    }

    public static TransactionRequest transactionControllerRequestToTransactionRequest(final TransactionControllerRequest transactionRequest) {
        return TransactionRequest.builder()
                .amount(transactionRequest.getAmount())
                .numberAccount(transactionRequest.getNumberAccount())
                .build();
    }

    public static HistoryControllerResponse historyResponseToHistoryControllerResponse(final HistoryResponse historyResponse) {
        return HistoryControllerResponse.builder()
                .date(historyResponse.getDate())
                .amount(historyResponse.getAmount())
                .build();
    }
}
