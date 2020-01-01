package com.app.users.Services;

import com.app.users.Models.UserDTO;
import com.app.users.Models.UserForCreationDTO;

import java.util.List;

public interface IUserService {
    public UserDTO getUser(Long usrId);
    public List<UserDTO> get();
    public UserDTO create(UserForCreationDTO user);
}
