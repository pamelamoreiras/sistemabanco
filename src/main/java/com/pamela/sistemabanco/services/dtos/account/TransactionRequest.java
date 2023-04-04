package com.pamela.sistemabanco.services.dtos.account;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionRequest {

    private BigDecimal amount;
    private Long numberAccount;
}
