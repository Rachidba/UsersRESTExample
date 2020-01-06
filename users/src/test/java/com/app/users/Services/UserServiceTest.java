package com.app.users.Services;

import com.app.users.Entities.User;
import com.app.users.Mappers.UserMapper;
import com.app.users.Models.UserDTO;
import com.app.users.Repositories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUser_ShouldReturnNull_WhenUserNotFound() {
        when(userRepository.findById(anyLong())).thenThrow(new EntityNotFoundException());

        UserDTO result = userService.getUser(new Long(1));
        assertNull(result);

        verify(userRepository).findById(new Long(1));
    }

    @Test
    public void getUser_ShouldReturnExactUser_WhenFound() {
        User user = new User(new Long(1), "Oumaima", "DAHHOUM");
        UserDTO expectedUser = new UserDTO(new Long(1), "Oumaima", "DAHHOUM");

        when(userRepository.findById(new Long(1))).thenReturn(Optional.of(user));
        when(userMapper.userToUserDTO(user)).thenReturn(expectedUser);

        UserDTO result = userService.getUser(new Long(1));
        assertEquals(expectedUser, result);

        verify(userRepository).findById(new Long(1));
    }

    @Test
    public void getUsers_ShouldReturnAllFoundUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User(new Long(1), "Rachid", "BAAZIZ"));
        users.add(new User(new Long(2), "Oumaima", "DAHHOUM"));

        List<UserDTO> expectedUsers = new ArrayList<>();
        expectedUsers.add(new UserDTO(new Long(1), "Rachid", "BAAZIZ"));
        expectedUsers.add(new UserDTO(new Long(2), "Oumaima", "DAHHOUM"));

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.usersToUsersDTO(users)).thenReturn(expectedUsers);

        List<UserDTO> result = userService.get();

        assertArrayEquals(expectedUsers.toArray(), result.toArray());

        verify(userRepository).findAll();
    }

}
