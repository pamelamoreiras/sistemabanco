package com.pamela.sistemabanco.client;

import com.pamela.sistemabanco.models.Account;
import com.pamela.sistemabanco.models.Client;
import com.pamela.sistemabanco.repositories.ClientRepository;
import com.pamela.sistemabanco.services.dtos.client.ClientServiceRequest;
import com.pamela.sistemabanco.services.impl.ClientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void whenNewClientIsInformedThenItShouldBeCreated() {

        final var clientRequest = ClientServiceRequest.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .build();

        final var expectClient = Client.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .build();

        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(expectClient);

        final var actualResult = clientService.create(clientRequest);

        assertNotNull(actualResult);
        Assertions.assertEquals(expectClient.getName(), actualResult.getName());
        Assertions.assertEquals(expectClient.getDocument(), actualResult.getDocument());
        Assertions.assertEquals(expectClient.getAddress(), actualResult.getAddress());
    }



    @Test
    void whenValidDocumentIsGivenThenAnClientShouldBeReturned() {

        final var clientRequest = ClientServiceRequest.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .build();

        final var expectClient = Client.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .accounts(List.of(Account.builder()
                        .build())
                )
                .build();

        Mockito.when(clientRepository.findByDocument(clientRequest.getDocument())).thenReturn(expectClient);

        final var actualResult = clientService.getClientDetailsByDocument(clientRequest.getDocument());

        assertNotNull(actualResult);
        Assertions.assertEquals(expectClient.getName(), actualResult.getName());
        Assertions.assertEquals(expectClient.getDocument(), actualResult.getDocument());
        Assertions.assertEquals(expectClient.getAddress(), actualResult.getAddress());
    }

    @Test
    void  whenListClientsIsCalledThenItShouldBeReturned() {

        final var expectClient = Client.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .accounts(List.of(Account.builder()
                        .build())
                )
                .build();

        Mockito.when(clientRepository.findAll()).thenReturn(List.of(expectClient));

        final var actualResult = clientService.findAll();

        assertNotNull(actualResult);
    }

    @Test
    void  whenListClientsDatailsIsCalledThenItShouldBeReturned() {

        final var expectClient = Client.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .accounts(List.of(Account.builder()
                        .build())
                )
                .build();

        Mockito.when(clientRepository.findAll()).thenReturn(List.of(expectClient));

        final var actualResult = clientService.findAllClientsDetails();

        assertNotNull(actualResult);
    }

    @Test
    void whenValidDocumentIsGivenThenAnClientShouldBeEdited() {

        final var clientRequest = ClientServiceRequest.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .build();

        final var expectClient = Client.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .accounts(List.of(Account.builder()
                        .build())
                )
                .build();

        Mockito.when(clientRepository.findByDocument(clientRequest.getDocument())).thenReturn(expectClient);

        Mockito.when(clientRepository.save(Mockito.any())).thenReturn(expectClient);

        final var actualResult = clientService.update(clientRequest.getDocument(), clientRequest);

        assertNotNull(actualResult);
        Assertions.assertEquals(expectClient.getDocument(), actualResult.getDocument());
    }

    @Test
    void whenValidClientDocumentIsGivenThenItShouldBeDeleted() {
        final var clientRequest = ClientServiceRequest.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .build();

        final var expectClient = Client.builder()
                .name("Pamela Moreira")
                .document("14496107651")
                .address("Rua Nilo")
                .accounts(List.of(Account.builder()
                        .build())
                )
                .build();

        Mockito.when(clientRepository.findByDocument(clientRequest.getDocument())).thenReturn(expectClient);

        Mockito.doNothing().when(clientRepository).removeClientByDocument(clientRequest.getDocument());

        clientService.delete(clientRequest.getDocument());

        Mockito.verify(clientRepository,Mockito.times(1)).removeClientByDocument(Mockito.any());
    }

}
