package com.BJJ.BJJSite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.FactoryExceptions.UserAlreadyExistsException;
import com.BJJ.BJJSite.Repositories.UserRepository;

import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public <T extends User> T createUser(T user) throws UserAlreadyExistsException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
        }
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long userId, User update) {
        return userRepository.findById(userId).map(user -> {
            if (update.getFullName() != null) {
                user.setFullName(update.getFullName());
            }
            if (update.getUsername() != null) {
                user.setUsername(update.getUsername());
            }
            if (update.getPassword() != null) {
                user.setPassword(update.getPassword());
            }
            if (update.getEmail() != null) {
                user.setEmail(update.getEmail());
            }
            if (update.getPhone() != null) {
                user.setPhone(update.getPhone());
            }
            if (update.getAddress() != null) {
                user.setAddress(update.getAddress());
            }
            if (update.getSex() != null) {
                user.setSex(update.getSex());
            }
            if (update.getDob() != null) {
                user.setDob(update.getDob());
            }
            if (update.getPaymentOptions() != null) {
                user.getPaymentOptions().clear();
                user.getPaymentOptions().addAll(update.getPaymentOptions());
            }
            return userRepository.save(user);
        });
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
