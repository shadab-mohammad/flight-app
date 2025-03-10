package com.examly.springapp.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.exception.EntityNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.model.UserToken;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.UserService;

/**
 * This is a UserService class that performs basic operations on User entity.
 * 
 * @author mohammadShadab
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    /**
     * Creates a user 
     * @param user
     * @return
     * @throws EntityNotFoundException
     */

    @Override
    public User createUser(User user) throws EntityNotFoundException {
        User existingUser = userRepo.findByUsername(user.getUsername());
        if(existingUser != null){
            throw new EntityNotFoundException("User Already Exists!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    /**
     * validates the user for login
     * @param user
     * @return
     */
    @Override
    public UserToken loginUser(User user) {
        UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());
        User existingUser = userRepo.findByUsername(user.getUsername());
        if(passwordEncoder.matches(user.getPassword(), existingUser.getPassword())){
            String token = jwtUtils.generateToken(details);
            return new UserToken(user.getUsername(),existingUser.getUserId(),token,existingUser.getUserRole());
        }
        return null;
    }

    /**
     * Retrieves a user by ID
     * @param userId
     * @return
     * @throws EntityNotFoundException
     */
    @Override
    public User getUserById(int userId) throws EntityNotFoundException {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException("User not found.");
        }
        return user;
    }

    /**
     * Retrieves a list of users
     * @return
     */
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByUsername(String username) throws EntityNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user==null){
            throw new EntityNotFoundException("User not found");
        }
        return user;
    }
    
    
    

}
