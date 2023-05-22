package com.vehiculos.services;

import com.vehiculos.dto.UserCarResponseDto;
import com.vehiculos.models.User;

import java.util.List;

public interface UserService {
    UserCarResponseDto getUser(Long id);
    Boolean createUser(User user);
    List<UserCarResponseDto> allUser();
    Boolean updateUser(Long id, User user);
    String login(User user);
}
