package com.stuvio.backend.service;

import com.stuvio.backend.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.stuvio.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.stuvio.backend.dto.LoginRequest;
import com.stuvio.backend.dto.LoginResponse;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, 
         BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {

    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
        throw new RuntimeException("Email already exists");
    }

    user.setPassword(
            passwordEncoder.encode(user.getPassword())
    );

    return userRepository.save(user);
}

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
public LoginResponse login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    boolean passwordMatches = passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
    );

    if (!passwordMatches) {
        throw new RuntimeException("Invalid password");
    }

    return new LoginResponse("Login successful");
}
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

       user.setName(updatedUser.getName());
user.setEmail(updatedUser.getEmail());

user.setPassword(
        passwordEncoder.encode(updatedUser.getPassword())
);

return userRepository.save(user);
    }

    public String deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            return "User not found";
        }

        userRepository.deleteById(id);

        return "User deleted successfully";
    }
}