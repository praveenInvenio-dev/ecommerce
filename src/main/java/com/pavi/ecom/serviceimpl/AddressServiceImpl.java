package com.pavi.ecom.serviceimpl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavi.ecom.Exception.NotFoundException;
import com.pavi.ecom.dto.AddressDTO;
import com.pavi.ecom.mapper.AddressMapper;
import com.pavi.ecom.model.Address;
import com.pavi.ecom.model.Users;
import com.pavi.ecom.repository.AddressRepository;
import com.pavi.ecom.repository.UsersRepository;
import com.pavi.ecom.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    @Autowired
    private UsersRepository userRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address entity = addressMapper.addressDTOToAddress(addressDTO);
        Users user = userRepository.findById(addressDTO.getUserid()).get();
        entity.setUsers(user);
        return addressMapper.addressToAddressDTO(addressRepository.save(entity));
    }

    @Override
    public AddressDTO getAddressById(Long id) throws NotFoundException {
    	Address entity = addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found"));
        return addressMapper.addressToAddressDTO(entity);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> entities = addressRepository.findAll();
        return entities.stream()
                .map(addressMapper::addressToAddressDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) throws NotFoundException {
        if (!addressRepository.existsById(id)) {
            throw new NotFoundException("Address not found");
        }

        Address entity = addressMapper.addressDTOToAddress(addressDTO);
        entity.setId(id);

        return addressMapper.addressToAddressDTO(addressRepository.save(entity));
    }

    @Override
    public void deleteAddress(Long id) throws NotFoundException {
        if (!addressRepository.existsById(id)) {
            throw new NotFoundException("Address not found");
        }

        addressRepository.deleteById(id);
    }
}
