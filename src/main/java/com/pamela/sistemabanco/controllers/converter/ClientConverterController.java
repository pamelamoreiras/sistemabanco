package com.pamela.sistemabanco.controllers.converter;

import com.pamela.sistemabanco.controllers.dtos.client.ClientControllerRequest;
import com.pamela.sistemabanco.controllers.dtos.client.ClientControllerResponse;
import com.pamela.sistemabanco.controllers.dtos.client.ClientDetailsControllerResponse;
import com.pamela.sistemabanco.services.converter.AccountConverter;
import com.pamela.sistemabanco.services.dtos.client.ClientDetailsResponse;
import com.pamela.sistemabanco.services.dtos.client.ClientServiceRequest;
import com.pamela.sistemabanco.services.dtos.client.ClientServiceResponse;

public class ClientConverterController {

    public static ClientServiceRequest clientRequestToClientControllerRequest(final ClientControllerRequest clientRequest) {
        return ClientServiceRequest.builder()
                .name(clientRequest.getName())
                .document(clientRequest.getDocument())
                .address(clientRequest.getAddress())
                .build();
    }

    public static ClientControllerResponse clientResponseToClientControllerResponse(final ClientServiceResponse clientResponse) {
        return ClientControllerResponse.builder()
                .name(clientResponse.getName())
                .document(clientResponse.getDocument())
                .address(clientResponse.getAddress())
                .build();
    }

    public static ClientDetailsControllerResponse clientToClientDetails(final ClientDetailsResponse client) {
        return ClientDetailsControllerResponse.builder()
                .name(client.getName())
                .document(client.getDocument())
                .address(client.getAddress())
                .accounts(client.getAccounts().stream().map(AccountConverterController::accountToClientDetailsServiceToAccountDetailsControllerResponse).toList())
                .build();
    }
}
