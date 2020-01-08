package com.app.users.Mappers;

import com.app.users.Entities.User;
import com.app.users.Models.RegistrationDTO;
import com.app.users.Models.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper{
    User userDTOToUser(final UserDTO userDTO);
    UserDTO userToUserDTO(final User user);
    List<UserDTO> usersToUsersDTO(final List<User> users);
    User userForCreationDTOToUser(final RegistrationDTO registrationDTO);
}
