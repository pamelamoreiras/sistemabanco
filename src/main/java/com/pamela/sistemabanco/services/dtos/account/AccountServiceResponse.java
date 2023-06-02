package com.pamela.sistemabanco.services.dtos.account;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountServiceResponse {

    private Long id;
    private String name;
    private String document;
    private Long accountNumber;
    private BigDecimal balance;
}
