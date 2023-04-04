package com.pamela.sistemabanco.services.dtos.account;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class HistoryResponse {
    private LocalDateTime date;
    private BigDecimal amount;
}
