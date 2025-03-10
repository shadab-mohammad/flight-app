package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.exception.EntityNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.model.UserToken;

public interface UserService {
    User createUser(User user) throws EntityNotFoundException;
    UserToken loginUser(User user);
    User getUserById(int userId) throws EntityNotFoundException;
    List<User> getUsers();
    User getUserByUsername(String username) throws EntityNotFoundException;
}
