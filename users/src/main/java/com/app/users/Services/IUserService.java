package com.app.users.Services;

import com.app.users.Models.UserDTO;
import com.app.users.Models.RegistrationDTO;

import java.util.List;

public interface IUserService {
    UserDTO getUser(Long usrId);
    List<UserDTO> get();
    UserDTO create(RegistrationDTO user);
    UserDTO getUser(String email);
}
