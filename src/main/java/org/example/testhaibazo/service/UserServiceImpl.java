package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.UserDTO;
import org.example.testhaibazo.model.User;
import org.example.testhaibazo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = convertToEntity(userDto);
        return convertToDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDto) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            return convertToDto(userRepository.save(user));
        }).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDto(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getAddress());
    }

    private User convertToEntity(UserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        return user;
    }
}
