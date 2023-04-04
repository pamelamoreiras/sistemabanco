package com.pamela.sistemabanco.services;

import com.pamela.sistemabanco.services.dtos.client.ClientDetailsResponse;
import com.pamela.sistemabanco.services.dtos.client.ClientServiceRequest;
import com.pamela.sistemabanco.services.dtos.client.ClientServiceResponse;

import java.util.List;

public interface ClientService {

    ClientServiceResponse create(final ClientServiceRequest clientRequest);

    List<ClientServiceResponse> findAll();

    ClientServiceResponse update(final String document, final ClientServiceRequest clientRequest);

    void delete(final String document);

    ClientDetailsResponse getClientDetailsByDocument(final String document);

    List<ClientDetailsResponse> findAllClientsDetails();
}
