package com.app.users.Services;

import com.app.users.Entities.User;
import com.app.users.Models.UserDTO;
import com.app.users.Models.UserForCreationDTO;
import com.app.users.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO getUser(Long userId) {
        User user;
        try {
            user = userRepository.findById(userId).get();
        } catch (Exception exception){
            return null;
        }

        return userToUserDTO(user);
    }

    @Override
    public List<UserDTO> get() {
        List<User> users = userRepository.findAll();
        return usersToUsersDTO(users);
    }

    @Override
    public UserDTO create(UserForCreationDTO userDTO) {
        User user = userForCreationToUser(userDTO);
        User createdUser = userRepository.save(user);
        return userToUserDTO(createdUser);
    }

    private UserDTO userToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private List<UserDTO> usersToUsersDTO(List<User> users) {
        Type listType = new TypeToken<List<UserDTO>>(){}.getType();
        return modelMapper.map(users, listType);
    }

    private User userForCreationToUser(UserForCreationDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
