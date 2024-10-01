package com.example.pdsbackend.service;

import com.example.pdsbackend.DTO.UserDTO;
import com.example.pdsbackend.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public User createUser(UserDTO user);
    public void deleteUser(Long id);
    public Optional<User> searchUserById(Long id);
    public List<User> listUsers();
    public User editUser(Long id, UserDTO user);
}