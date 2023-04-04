package com.pamela.sistemabanco.controllers.dtos.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountControllerRequest {

    @NotBlank
    private String document;

    @NotNull
    private Long accountNumber;
}
