package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    UserDTO createUser(UserDTO userDto);

    UserDTO updateUser(Long id, UserDTO userDto);

    void deleteUser(Long id);
}
