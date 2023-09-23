package com.pavi.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pavi.ecom.dto.AddressDTO;
import com.pavi.ecom.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	AddressDTO addressToAddressDTO(Address address);

	Address addressDTOToAddress(AddressDTO addressDTO);

	List<AddressDTO> addressesToAddressDTOs(List<Address> addresses);

	List<Address> addressDTOsToAddresses(List<AddressDTO> addressDTOs);

}
