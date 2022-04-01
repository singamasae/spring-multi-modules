package com.guestpro.iot.emoney.repository;


import com.guestpro.iot.emoney.model.Privilege;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrivilegeRepository extends PagingAndSortingRepository<Privilege, String> {
    Privilege findByName(String name);
}
