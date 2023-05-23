package com.personal.dronya.service.impl;

import com.personal.dronya.model.dto.dron.DroneDTO;
import com.personal.dronya.model.dto.user.UserDTO;
import com.personal.dronya.model.dto.user_dron.UserDroneDTO;
import com.personal.dronya.model.entity.Drone;
import com.personal.dronya.model.entity.User;
import com.personal.dronya.model.entity.UserDrone;
import com.personal.dronya.model.entity.UserDroneID;
import com.personal.dronya.model.enums.DroneStatus;
import com.personal.dronya.repository.DroneRepository;
import com.personal.dronya.repository.UserDroneRepository;
import com.personal.dronya.repository.UserRepository;
import com.personal.dronya.service.IUserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private UserDroneRepository userDroneRepository;


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User newUser) {
        newUser.setName(Strings.concat(newUser.getName(), " Updated"));
        return userRepository.save(newUser);
    }

    @Override
    public UserDTO getUser(Integer id) {
        return userRepository.findById(id).map(this::mapperUserToUserDTO).orElse(UserDTO.builder().name("User not found").build());
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(this::mapperUserToUserDTO).collect(Collectors.toList());
    }

    @Override
    public String rentDrone(Integer userId, Integer droneId) {
        Optional<User> userDBOptional = userRepository.findById(userId);
        Optional<Drone> droneDBOptional = droneRepository.findById(droneId);
        if (userDBOptional.isEmpty() || droneDBOptional.isEmpty()) {
            return "Error, userId or droneId is not valid.";
        }

        User userDB = userDBOptional.get();
        Drone droneDB = droneDBOptional.get();
        droneDB.setStatus(DroneStatus.RENTED);

        //UserDB
        UserDroneID userDroneId = UserDroneID.builder().userId(userId).droneId(droneId).build();
        if (userDroneRepository.findById(userDroneId).isPresent()) {
            return String.format("El usuario %s ya tiene rentado el dron: %s.", userId, droneId);
        }

        UserDrone userDrone = UserDrone.builder().userDroneId(userDroneId).user(userDB).drone(droneDB).build();
        droneDB.setRentedBy(userDrone);
        System.out.println("User drone DB: "+droneDB.getRentedBy());
        //userDroneRepository.save(userDrone);
        droneDB = droneRepository.save(droneDB);
        userDrone.setDrone(droneDB);
        userDroneRepository.save(userDrone);


        return "Drone rented successfully";
    }

    @Override
    public String removeDroneRented(Integer userId, Integer droneId) {
        Optional<User> userDBOptional = userRepository.findById(userId);
        Optional<Drone> droneDBOptional = droneRepository.findById(droneId);
        if (userDBOptional.isEmpty() || droneDBOptional.isEmpty()) {
            return "Error, userId or droneId is not valid.";
        }

        User userDB = userDBOptional.get();
        Drone droneDB = droneDBOptional.get();
        droneDB.setStatus(DroneStatus.AVAILABLE);
        UserDroneID userDroneId = UserDroneID.builder().userId(userId).droneId(droneId).build();
        Optional<UserDrone> userDrone = userDroneRepository.findById(userDroneId);
        if (userDrone.isEmpty()) {
            return String.format("User with id %s has not rented drone with id: %s.", userId, droneId);
        }
        droneRepository.save(droneDB);
        userDroneRepository.deleteById(userDroneId);
        System.out.println("FIND IN DB: "+userDroneRepository.findById(userDroneId));
        return "Drone has been unrented successfully.";
    }

    private UserDTO mapperUserToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .droneRented(user.getDroneRented().stream().map(this::mapperUserDroneToUserDroneDTO).collect(Collectors.toList()))
                .build();
    }

    private UserDroneDTO mapperUserDroneToUserDroneDTO(UserDrone userDrone) {
        return UserDroneDTO.builder()
                .droneDTO(mapperDroneToDroneDTO(userDrone.getDrone()))
                .build();
    }

    private DroneDTO mapperDroneToDroneDTO(Drone drone) {
        return DroneDTO.builder()
                .id(drone.getId())
                .name(drone.getName())
                .type(drone.getType())
                .battery(drone.getBattery())
                .status(drone.getStatus())
                .build();
    }
}
