package com.reservation.dao;

import com.reservation.model.User;

import java.util.List;

public interface IUserDAO {
    boolean addUser(User user);
    List<User> getAllUsers();
}
