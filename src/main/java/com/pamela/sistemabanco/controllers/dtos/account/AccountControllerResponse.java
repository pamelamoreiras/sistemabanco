package com.pamela.sistemabanco.controllers.dtos.account;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountControllerResponse {

    private String name;
    private String document;
    private Long numberAccount;
    private BigDecimal balance;
}
