package org.example.dao;

import org.example.entities.User;

/*In this interface we write the methods related to user*/
public interface UserDao {

    void register(User user);

    User getByEmail(String email);

    User getUserById(int userId);

}
