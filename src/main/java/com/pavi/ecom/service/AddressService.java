package com.pavi.ecom.service;

import java.util.List;

import com.pavi.ecom.Exception.NotFoundException;
import com.pavi.ecom.dto.AddressDTO;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO);
    AddressDTO getAddressById(Long id) throws NotFoundException;
    List<AddressDTO> getAllAddresses();
    AddressDTO updateAddress(Long id, AddressDTO addressDTO) throws NotFoundException;
    void deleteAddress(Long id) throws NotFoundException;
}
