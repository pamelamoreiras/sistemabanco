package com.pamela.sistemabanco.repositories;

import com.pamela.sistemabanco.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByDocument(final String document);

    void removeClientByDocument(final String document);
}
