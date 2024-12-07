package com.soa_.ManageFootballStadium.controller;

import com.soa_.ManageFootballStadium.model.User;
import com.soa_.ManageFootballStadium.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User createdUser = userService.register(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(401).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
