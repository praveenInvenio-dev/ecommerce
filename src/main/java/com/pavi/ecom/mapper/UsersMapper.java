package com.pavi.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.pavi.ecom.dto.UsersDTO;
import com.pavi.ecom.model.Users;

@Mapper(componentModel = "spring")
public interface UsersMapper {
	UsersDTO usersToUsersDTO(Users users);

	Users usersDTOToUsers(UsersDTO usersDTO);

	List<UsersDTO> usersToUsersDTOs(List<Users> usersList);

	List<Users> usersDTOsToUsers(List<UsersDTO> usersDTOs);

}
