package com.vehiculos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
public class UserCarResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String password;
    private Date birthday;
    private List<CarDto> cars;

    public UserCarResponseDto(Long id, String firstName, String lastName, String address,
                              String email, String password, Date birthday, List<CarDto> cars) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.cars = cars;
    }


}
