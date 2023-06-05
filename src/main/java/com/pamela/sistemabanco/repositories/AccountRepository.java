package com.pamela.sistemabanco.repositories;

import com.pamela.sistemabanco.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByAccountNumber(final Long number);
}
