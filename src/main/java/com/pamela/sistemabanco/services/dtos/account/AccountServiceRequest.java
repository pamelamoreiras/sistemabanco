package com.pamela.sistemabanco.services.dtos.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountServiceRequest {

    @NotBlank
    private String document;

    @NotNull
    private Long accountNumber;
}
