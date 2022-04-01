package com.guestpro.iot.emoney.repository;

import com.guestpro.iot.emoney.model.TransactionDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionDetailRepository extends PagingAndSortingRepository<TransactionDetail, String> {
}
