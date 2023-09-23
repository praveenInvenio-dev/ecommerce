package com.pavi.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavi.ecom.model.Users;
import com.pavi.ecom.model.Cart;

public interface xyzrepo  extends JpaRepository<Cart, Long> {

}
