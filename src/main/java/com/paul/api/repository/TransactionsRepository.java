package com.paul.api.repository;

import com.paul.api.models.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM transactions WHERE account_id = ?1", nativeQuery = true)
    List<Transaction> findByNombre(Long id);
}
