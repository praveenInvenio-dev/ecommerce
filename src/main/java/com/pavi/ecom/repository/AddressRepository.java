package com.pavi.ecom.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pavi.ecom.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // You can add custom query methods here if needed
}
