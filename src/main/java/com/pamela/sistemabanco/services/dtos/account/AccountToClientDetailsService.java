package com.pamela.sistemabanco.services.dtos.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class AccountToClientDetailsService {

    private Long numberAccount;
    private BigDecimal balance;
}
