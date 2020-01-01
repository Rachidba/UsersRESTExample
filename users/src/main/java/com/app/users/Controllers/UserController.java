package com.app.users.Controllers;
import com.app.users.Models.UserDTO;
import com.app.users.Models.UserForCreationDTO;
import com.app.users.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> GetUsers() {
        List<UserDTO> users = userService.get();
        if (users.size() == 0)
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No users found");
        return userService.get();
    }

    @GetMapping("/{userId}")
    public UserDTO GetUser(@PathVariable Long userId) {
        UserDTO user = userService.getUser(userId);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found with the given ID");
        return user;
    }

    @PostMapping
    public UserDTO CreateUser(@RequestBody UserForCreationDTO userDTO) {
        if (userDTO == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        UserDTO createdUser = userService.create(userDTO);
        return createdUser;
    }
}
