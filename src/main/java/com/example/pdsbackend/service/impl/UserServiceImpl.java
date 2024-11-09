package com.example.pdsbackend.service.impl;

import com.example.pdsbackend.DTO.UserDTO;
import com.example.pdsbackend.model.User;
import com.example.pdsbackend.repository.IUserRepository;
import com.example.pdsbackend.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final PasswordEncoder passwordEncoder;
    IUserRepository userRepository;

    /* DESCOMENTAR
    @Autowired
    private PasswordEncoder passwordEncoder;
    /*

     */
    @Autowired
    public UserServiceImpl(IUserRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setCreatedAt(LocalDateTime.now()); // creation date

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> searchUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User editUser(Long id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setEmail(userDTO.getEmail());
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException("User with ID " + id + " not found.");
        }
    }

    /*
    @Override
    public List<Libro> listLibrosFromAutor(Long id) {
        List<Libro> librosFromAutor = new ArrayList<>();

        if (searchAuthor(id).isPresent()) {
            for (Libro l :
                    libroService.listLibros()) {
                if (l.getAutor().getId().equals(id)) {
                    librosFromAutor.add(l);
                }
            }
        } else {
            return null;
        }
        return librosFromAutor;
    }*/
}