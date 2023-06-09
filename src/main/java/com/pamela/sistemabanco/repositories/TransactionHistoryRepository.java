package com.pamela.sistemabanco.repositories;

import com.pamela.sistemabanco.models.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> getAllTransactionHistoryByAccountId(final Long accountId);
}
