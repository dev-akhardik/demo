package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.entity.User;
import demo.respository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // POST method to save a new user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {

        // Save the user to the database
        userRepository.save(user);

        return ResponseEntity.ok("User created successfully");

    }

    // GET method to read a user by id
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    // PUT method to update an existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    // DELETE method to delete a user by id
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return "User with id " + id + " has been deleted";
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    // GET method to fetch all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}