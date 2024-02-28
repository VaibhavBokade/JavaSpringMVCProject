package org.example.handler;

import org.example.dto.LoginDto;
import org.example.dto.UserDto;
import org.example.entities.User;

public interface UserHandler {

    UserDto login(LoginDto loginDto);

    String register(User user);

    void validateUser(User user);

    UserDto displayDashboard(int userId);
}
