package com.pamela.sistemabanco.services.exceptions.account;

public class MaximumNumberOfAccountsCreatedException extends RuntimeException {

    public MaximumNumberOfAccountsCreatedException(String message) {
        super(message);
    }

    public MaximumNumberOfAccountsCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
