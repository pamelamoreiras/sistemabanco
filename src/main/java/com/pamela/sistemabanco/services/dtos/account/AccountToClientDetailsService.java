package com.pamela.sistemabanco.services.dtos.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class AccountToClientDetailsService {

    private Long id;
    private Long accountNumber;
    private BigDecimal balance;
}
