package com.vehiculos.services;

import com.vehiculos.dto.UserAuthDto;
import com.vehiculos.dto.UserCarResponseDto;
import com.vehiculos.exception.PasswordIncorrectException;
import com.vehiculos.exception.UserNotFoundException;
import com.vehiculos.mapper.UserToUserCarResponseDto;
import com.vehiculos.models.User;
import com.vehiculos.repository.UserRepository;
import com.vehiculos.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.vehiculos.utils.Constants.PASSWORD_INCORRECT;
import static com.vehiculos.utils.Constants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JWTUtil jwtUtil;

    @Override
    public UserCarResponseDto getUser(Long id) {
        Optional<User> userFind = userRepository.findById(id);
        if (userFind.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        return UserToUserCarResponseDto.userCarToUserDto(userFind.get());
    }

    @Override
    public void createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public List<UserCarResponseDto> allUser() {
        return userRepository.findAll()
                .stream()
                .map(UserToUserCarResponseDto::userCarToUserDto)
                .toList();
    }

    @Override
    public void updateUser(Long id, User user) {

        Optional<User> userFind = userRepository.findById(id);
        if (userFind.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        User userBD = userFind.get();
        userBD.setFirstName(user.getFirstName());
        userBD.setLastName(user.getLastName());
        userBD.setBirthday(user.getBirthday());
        userBD.setAddress(user.getAddress());
        userBD.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userBD);

    }

    @Override
    public String login(UserAuthDto user) {
        Optional<User> userBd = userRepository.findByEmail(user.getEmail());
        if (userBd.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(user.getPassword(), userBd.get().getPassword())) {
            throw new PasswordIncorrectException(PASSWORD_INCORRECT);
        }
        return jwtUtil.create(String.valueOf(
                        userBd.get().getId()),
                user.getEmail());
    }


}

