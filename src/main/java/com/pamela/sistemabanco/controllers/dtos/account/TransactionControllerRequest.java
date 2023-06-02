package com.pamela.sistemabanco.controllers.dtos.account;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionControllerRequest {

    private BigDecimal amount;
    private Long accountNumber;
}
