package com.personal.dronya.service;

import com.personal.dronya.model.dto.user.UserDTO;
import com.personal.dronya.model.entity.User;

import java.util.List;

public interface IUserService {

    User saveUser(User user);
    User updateUser(User newUser);
    UserDTO getUser(Integer id);
    List<UserDTO> findAllUsers();

    String rentDrone(Integer userId, Integer droneId);

    String removeDroneRented(Integer userId, Integer droneId);
}
