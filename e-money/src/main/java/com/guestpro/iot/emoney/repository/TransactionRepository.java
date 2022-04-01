package com.guestpro.iot.emoney.repository;

import com.guestpro.iot.emoney.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, String> {
}
