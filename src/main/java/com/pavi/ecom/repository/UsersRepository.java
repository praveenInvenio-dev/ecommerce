package com.pavi.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pavi.ecom.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByEmail(String username);
    // You can add custom query methods here if needed
}