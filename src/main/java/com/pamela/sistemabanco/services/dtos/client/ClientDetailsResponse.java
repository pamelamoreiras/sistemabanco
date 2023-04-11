package com.pamela.sistemabanco.services.dtos.client;

import com.pamela.sistemabanco.models.Account;
import com.pamela.sistemabanco.services.dtos.account.AccountServiceResponse;
import com.pamela.sistemabanco.services.dtos.account.AccountToClientDetailsService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDetailsResponse {

    private String name;
    private String document;
    private String address;
    private List<AccountToClientDetailsService> accounts;
}
