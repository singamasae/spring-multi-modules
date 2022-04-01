package com.guestpro.iot.emoney.repository;


import com.guestpro.iot.emoney.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, String> {
}
