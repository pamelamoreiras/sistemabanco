package com.pamela.sistemabanco.controllers;

import com.pamela.sistemabanco.controllers.converter.ClientConverterController;
import com.pamela.sistemabanco.controllers.dtos.client.ClientControllerRequest;
import com.pamela.sistemabanco.controllers.dtos.client.ClientControllerResponse;
import com.pamela.sistemabanco.controllers.dtos.client.ClientDetailsControllerResponse;
import com.pamela.sistemabanco.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController{

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientControllerResponse> createClient(@RequestBody @Valid final ClientControllerRequest clientControllerRequest) {
        final var convertClientRequest = ClientConverterController.clientRequestToClientControllerRequest(clientControllerRequest);

        final var clientToCreate = clientService.create(convertClientRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ClientConverterController.clientResponseToClientControllerResponse(clientToCreate));
    }

    @GetMapping
    public ResponseEntity<List<ClientControllerResponse>> getAllClients() {
        final var clients = clientService.findAll();
        final var clientConvert = clients.stream()
                .map(ClientConverterController::clientResponseToClientControllerResponse)
                .toList();

        return ResponseEntity.ok().body(clientConvert);
    }

    @PutMapping("/{document}")
    public ResponseEntity<ClientControllerResponse> updateClient (@PathVariable final String document, @RequestBody  @Valid final ClientControllerRequest clientControllerRequest) {
        final var convertClientRequest = ClientConverterController.clientRequestToClientControllerRequest(clientControllerRequest);

        final var clientToUpdate = clientService.update(document, convertClientRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ClientConverterController.clientResponseToClientControllerResponse(clientToUpdate));
    }

    @DeleteMapping("/{document}")
    public ResponseEntity<Void> delete(@PathVariable final String document) {
        clientService.delete(document);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/details/{document}")
    public ResponseEntity<ClientDetailsControllerResponse> getClientDetailsByDocument(@PathVariable final String document) {
        final var client = clientService.getClientDetailsByDocument(document);

        return ResponseEntity.ok(ClientConverterController.clientToClientDetails(client));
    }

    @GetMapping("/details")
    public ResponseEntity<List<ClientDetailsControllerResponse>> getClientDetailsByDocument() {
        final var clients = clientService.findAllClientsDetails();

        final var convertClients = clients.stream()
                .map(ClientConverterController::clientToClientDetails)
                .toList();

        return ResponseEntity.ok().body(convertClients);
    }
}
