package com.pamela.sistemabanco.controllers.dtos.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class AccountToClientDetailsController {

    private Long id;
    private Long accountNumber;
    private BigDecimal balance;
}
