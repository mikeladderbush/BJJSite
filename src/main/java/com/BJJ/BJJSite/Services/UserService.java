package com.BJJ.BJJSite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public <T extends User> T createUser(T user) {
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User update) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (update.getFirstName() != null) {
                user.setFirstName(update.getFirstName());
            }
            if (update.getLastName() != null) {
                user.setLastName(update.getLastName());
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

            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
