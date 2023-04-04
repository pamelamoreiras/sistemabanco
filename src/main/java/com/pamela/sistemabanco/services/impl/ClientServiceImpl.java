package com.pamela.sistemabanco.services.impl;

import com.pamela.sistemabanco.models.Client;
import com.pamela.sistemabanco.repositories.ClientRepository;
import com.pamela.sistemabanco.services.ClientService;
import com.pamela.sistemabanco.services.converter.ClientConverter;
import com.pamela.sistemabanco.services.dtos.client.ClientDetailsResponse;
import com.pamela.sistemabanco.services.dtos.client.ClientServiceRequest;
import com.pamela.sistemabanco.services.dtos.client.ClientServiceResponse;
import com.pamela.sistemabanco.services.exceptions.client.ClientAlreadyExistsException;
import com.pamela.sistemabanco.services.exceptions.client.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepoository;

    @Override
    public ClientServiceResponse create(final ClientServiceRequest clientRequest) {

        Client client = getClientByDocument(clientRequest);

        if (client == null) {
            client = clientRepoository.save(ClientConverter.clientRequestToClient(clientRequest));
        } else {
            throw new ClientAlreadyExistsException("Cliente já existe");
        }

        return ClientConverter.clientToClientResponse(client);
    }

    @Override
    public List<ClientServiceResponse> findAll() {
        final var clients = clientRepoository.findAll();

        return clients.stream()
                .map(ClientConverter::clientToClientResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientServiceResponse update(final String document, final ClientServiceRequest clientRequest){
        Client client = getClientByDocument(clientRequest);

        if (client == null) {
            throw new ClientNotFoundException("Cliente não encontrado");
        } else if (client.getDocument().contains(document)){
            client.setName(clientRequest.getName());
            client.setDocument(clientRequest.getDocument());
            client.setAddress(clientRequest.getAddress());
        }

        final var clientToSave = clientRepoository.save(client);

        return ClientConverter.clientToClientResponse(clientToSave);
    }

    @Override
    @Transactional
    public void delete(final String document) {
        final var client = clientRepoository.findByDocument(document);

        if (client == null) {
            throw new ClientNotFoundException("Cliente não encontrado");
        } else if (client.getDocument().contains(document)) {
            clientRepoository.removeClientByDocument(document);
        }
    }

    @Override
    public ClientDetailsResponse getClientDetailsByDocument(final String document) {
        final var client = clientRepoository.findByDocument(document);

        if (client == null) {
            throw new ClientNotFoundException("Cliente não encontrado");
        }
        return ClientConverter.clientToClientDetails(client);
    }

    @Override
    public List<ClientDetailsResponse> findAllClientsDetails() {
        final var clients = clientRepoository.findAll();

        return clients.stream()
                .map(ClientConverter::clientToClientDetails)
                .collect(Collectors.toList());
    }


    private Client getClientByDocument(final ClientServiceRequest clientRequest) {
        return clientRepoository.findByDocument(clientRequest.getDocument());
    }
}
