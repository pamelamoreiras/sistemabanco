package com.pamela.sistemabanco.controllers.dtos.client;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientControllerRequest {

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @CPF
    @NotBlank(message = "CPF obrigatório")
    private String document;

    @NotBlank(message = "Campo obrigatório")
    private String address;
}
