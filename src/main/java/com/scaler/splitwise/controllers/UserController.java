package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.CreateUserDTO;
import com.scaler.splitwise.dtos.ResponseDTO;
import com.scaler.splitwise.dtos.UserDTO;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserDTO createUser(@RequestBody CreateUserDTO userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/user/{userId}")
    public ResponseDTO<UserDTO> getUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUser(userId);
        Optional<UserDTO> userDTO =  user.map(UserDTO::from);

        return userDTO.map(ResponseDTO::success).orElse(ResponseDTO.notFound());
    }

    @PutMapping("user/{userId}")
    public ResponseDTO<UserDTO> updateUser(@PathVariable Long userId, @RequestBody CreateUserDTO updateRequest) {
        UserDTO user = userService.updateUser(userId,updateRequest);
        if(user == null) {
            return ResponseDTO.notFound();
        }
        return ResponseDTO.success(user);

    }

}
