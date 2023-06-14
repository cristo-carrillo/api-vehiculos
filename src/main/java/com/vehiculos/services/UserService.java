package com.vehiculos.services;

import com.vehiculos.dto.UserAuthDto;
import com.vehiculos.dto.UserCarResponseDto;
import com.vehiculos.models.User;

import java.util.List;

public interface UserService {
    UserCarResponseDto getUser(Long id);
    void createUser(User user);
    List<UserCarResponseDto> allUser();
    void updateUser(Long id, User user);
    String login(UserAuthDto user);
}
