package com.pamela.sistemabanco.controllers.dtos.client;

import com.pamela.sistemabanco.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDetailsControllerResponse {

    private String name;
    private String document;
    private String address;
    private List<Account> accounts;
}
