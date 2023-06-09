package com.pamela.sistemabanco.controllers.dtos.account;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class HistoryControllerResponse {

    private Long id;
    private String date;
    private BigDecimal amount;
}
