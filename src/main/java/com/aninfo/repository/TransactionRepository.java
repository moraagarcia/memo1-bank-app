package com.aninfo.repository;

import com.aninfo.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Collection<Transaction> getTransactionsByCbu(Long cbu);

    Collection<Transaction> getTransactionsById(Long id);
}
