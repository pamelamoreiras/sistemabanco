package com.pamela.sistemabanco.services.converter;

import com.pamela.sistemabanco.models.Client;
import com.pamela.sistemabanco.services.dtos.client.ClientDetailsResponse;
import com.pamela.sistemabanco.services.dtos.client.ClientServiceRequest;
import com.pamela.sistemabanco.services.dtos.client.ClientServiceResponse;

import java.util.stream.Collectors;

public class ClientConverter {

    public static Client clientRequestToClient(final ClientServiceRequest clientRequest) {
        return Client.builder()
                .name(clientRequest.getName())
                .document(clientRequest.getDocument())
                .address(clientRequest.getAddress())
                .build();
    }

    public static ClientServiceResponse clientToClientResponse(final Client client) {
        return ClientServiceResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .document(client.getDocument())
                .address(client.getAddress())
                .build();
    }

    public static ClientDetailsResponse clientToClientDetails(final Client client) {
        return ClientDetailsResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .document(client.getDocument())
                .address(client.getAddress())
                .accounts(client.getAccounts().stream().map(AccountConverter::accountRequestToAccountResponse).toList())
                .build();
    }
}
