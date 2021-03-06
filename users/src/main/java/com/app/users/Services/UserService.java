package com.app.users.Services;

import com.app.users.Entities.User;
import com.app.users.Mappers.UserMapper;
import com.app.users.Models.UserDTO;
import com.app.users.Models.RegistrationDTO;
import com.app.users.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUser(Long userId) {
        User user;
        try {
            user = userRepository.findById(userId).get();
        } catch (Exception exception){
            return null;
        }

        return userMapper.userToUserDTO(user);
    }

    @Override
    public List<UserDTO> get() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUsersDTO(users);
    }

    @Override
    public UserDTO create(RegistrationDTO userDTO) {
        User user = userMapper.userForCreationDTOToUser(userDTO);
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
        User createdUser = userRepository.save(user);
        return userMapper.userToUserDTO(createdUser);
    }

    @Override
    public UserDTO getUser(String email) {
        User user = userRepository.findByEmail(email);

        return userMapper.userToUserDTO(user);
    }
}
