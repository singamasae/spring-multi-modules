package com.guestpro.iot.emoney.repository;


import com.guestpro.iot.emoney.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, String> {
    Role findByName(String name);
}
