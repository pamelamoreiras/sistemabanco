package com.pamela.sistemabanco.services.dtos.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientServiceResponse {

    private Long id;
    private String name;
    private String document;
    private String address;
}
