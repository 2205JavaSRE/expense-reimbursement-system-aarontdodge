package com.project1.dao;

import com.project1.models.User;

public interface UserDao {
    User getUserByLogin(String username, String password);
}
