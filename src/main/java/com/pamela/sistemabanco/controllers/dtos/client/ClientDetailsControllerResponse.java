package com.pamela.sistemabanco.controllers.dtos.client;

import com.pamela.sistemabanco.controllers.dtos.account.AccountControllerResponse;
import com.pamela.sistemabanco.controllers.dtos.account.AccountToClientDetailsController;
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

    private Long id;
    private String name;
    private String document;
    private String address;
    private List<AccountToClientDetailsController> accounts;
}
