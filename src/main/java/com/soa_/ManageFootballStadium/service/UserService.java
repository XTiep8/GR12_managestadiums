package com.soa_.ManageFootballStadium.service;

import com.soa_.ManageFootballStadium.model.User;
import com.soa_.ManageFootballStadium.model._enum.Role;
import com.soa_.ManageFootballStadium.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User register(User user) {
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }

    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    return userRepository.save(user);
                }).orElse(null);
    }

    public boolean deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
        return true;
    }
}
