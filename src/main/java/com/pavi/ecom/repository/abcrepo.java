package com.pavi.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pavi.ecom.model.Users;
import com.pavi.ecom.model.CartItem;

public interface abcrepo  extends JpaRepository<CartItem, Long> {

}
