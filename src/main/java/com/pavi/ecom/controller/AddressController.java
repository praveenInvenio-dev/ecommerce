package com.pavi.ecom.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavi.ecom.Exception.NotFoundException;
import com.pavi.ecom.dto.AddressDTO;
import com.pavi.ecom.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        AddressDTO createdAddress = addressService.createAddress(addressDTO);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) throws NotFoundException {
        AddressDTO address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) throws NotFoundException {
        AddressDTO updatedAddress = addressService.updateAddress(id, addressDTO);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) throws NotFoundException {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
