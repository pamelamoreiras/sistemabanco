package com.pamela.sistemabanco.controllers;

import com.pamela.sistemabanco.controllers.converter.AccountConverterController;
import com.pamela.sistemabanco.controllers.dtos.account.AccountControllerRequest;
import com.pamela.sistemabanco.controllers.dtos.account.AccountControllerResponse;
import com.pamela.sistemabanco.controllers.dtos.account.HistoryControllerResponse;
import com.pamela.sistemabanco.controllers.dtos.account.TransactionControllerRequest;
import com.pamela.sistemabanco.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountControllerResponse> createAccount(@RequestBody final AccountControllerRequest accountControllerRequest) {
        final var convertAccountRequest = AccountConverterController.accountControllerToAccountRequest(accountControllerRequest);

        final var accountToCreate = accountService.createAccount(convertAccountRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(AccountConverterController.accountServiceResponseToAccountControllerResponse(accountToCreate));
    }

    @DeleteMapping("/{numberAccount}")
    public ResponseEntity<Void> deleteAccount(@PathVariable final Long numberAccount) {

        accountService.deleteAccount(numberAccount);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@RequestBody final TransactionControllerRequest transactionControllerRequest) {
        final var convertAccountRequest = AccountConverterController.transactionControllerRequestToTransactionRequest(transactionControllerRequest);

        accountService.deposit(convertAccountRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@RequestBody final TransactionControllerRequest transactionControllerRequest) {
        final var convertAccountRequest = AccountConverterController.transactionControllerRequestToTransactionRequest(transactionControllerRequest);

        accountService.withdraw(convertAccountRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("history/{accountId}")
    public ResponseEntity<List<HistoryControllerResponse>> getHistoryByAccountId(@PathVariable final Long accountId) {
        final var history = accountService.getHistoryByAccountId(accountId);

        return ResponseEntity.status(HttpStatus.OK).body(
                history.stream()
                        .map(AccountConverterController::historyResponseToHistoryControllerResponse)
                        .toList());
    }


}
