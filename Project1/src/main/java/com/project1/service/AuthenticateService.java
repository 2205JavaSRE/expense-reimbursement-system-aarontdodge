package com.project1.service;

import com.project1.dao.UserDao;
import com.project1.dao.UserDaoImpl;
import com.project1.models.User;

public class AuthenticateService {

    UserDao uDao = new UserDaoImpl();
    public User authenticate(String username, String password) {
        User u = uDao.getUserByLogin(username, password);

        return u;
    }
}
