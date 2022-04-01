package com.guestpro.iot.emoney.repository;

import com.guestpro.iot.emoney.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
    @Query(value = "select u from User u join fetch u.roles where u.userName = :userName")
    User findByUserName(@Param("userName") String userName);
}
