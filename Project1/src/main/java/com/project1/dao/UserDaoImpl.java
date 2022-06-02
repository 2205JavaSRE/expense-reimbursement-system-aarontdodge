package com.project1.dao;

import com.project1.models.User;
import com.project1.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByLogin(String username, String password) {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM project1.users WHERE username = ? AND user_password = ?;";
        User loggedInUser = null;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                loggedInUser = new User(rs.getString("username"),
                                        rs.getString("user_password"),
                                        rs.getString("first_name"),
                                        rs.getString("last_name"),
                                        rs.getString("user_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedInUser;
    }
}
