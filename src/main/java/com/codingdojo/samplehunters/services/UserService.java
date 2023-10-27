package com.codingdojo.samplehunters.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.samplehunters.models.LoginUser;
import com.codingdojo.samplehunters.models.User;
import com.codingdojo.samplehunters.repositories.UserRepository;

    
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    public User register(User newUser, BindingResult result) {
        String[] fields = { "userName", "email", "password", "confirm" };
        for (String field : fields) {
            if (result.hasFieldErrors(field)) {
                System.out.println("Validation Error for field " + field + ": " + result.getFieldError(field));
                result.rejectValue(field, "error." + field, result.getFieldError(field).getDefaultMessage());
                return null;
            }
            if (field.equals("email") && userRepo.existsByEmail(newUser.getEmail())) {
                System.out.println("Email already exists: " + newUser.getEmail());
                result.rejectValue("email", "error.email", "Email already exists.");
                return null;
            }
            if (field.equals("confirm") && !newUser.getPassword().equals(newUser.getConfirm())) {
                System.out.println("Password and confirm password do not match.");
                result.rejectValue("confirm", "error.confirm", "Password and confirm password do not match.");
                return null;
            }
        }

        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);

        System.out.println("Saving newUser: " + newUser);
        return userRepo.save(newUser);
    }
    // This method will be called from the controller
    // whenever a user submits a login form.
        public User login(LoginUser newLoginObject, BindingResult result) {
        // TO-DO - Reject values:
        // 1. If email is NOT found in the DB
        // 2. If the passwords do not match
        // 3. If the user is not present
        // 4. If result has errors
        // Otherwise, return the user object

        if (result.hasErrors()) {
            return null;
        }
        // Find user in the DB by email
        Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail()); // this finds the user by email
        if (!potentialUser.isPresent()) { // this checks if the user is present and returns null if not 
            result.rejectValue("email", "Unique", "Email is taken/Email not found!"); // this is a custom error message that will be displayed on the page if the email is not found
            return null; 
        }
        // Reject if BCrypt password match fails
        User user = potentialUser.get(); // this gets the user from the optional
        if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) { // this checks if the password matches the hashed password
            result.rejectValue("password", "Matches", "Invalid Password!"); // this is a custom error message that will be displayed on the page if the password is incorrect
            return null;
        }
    
        // Otherwise, return the user object
        return user;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}