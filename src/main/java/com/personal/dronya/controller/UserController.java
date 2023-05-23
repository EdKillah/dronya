package com.personal.dronya.controller;

import com.personal.dronya.model.entity.User;
import com.personal.dronya.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(EndpointConstants.USER_BASE_URL)
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(EndpointConstants.USER_GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping(EndpointConstants.USER_GET_ALL)
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PostMapping(EndpointConstants.USER_SAVE)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.ACCEPTED);
    }

    @GetMapping(EndpointConstants.RENT_DRONE)
    public ResponseEntity<?> rentDrone(@RequestParam("userId") Integer userId, @RequestParam("droneId") Integer droneId) {
        return new ResponseEntity<>(userService.rentDrone(userId, droneId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(EndpointConstants.REMOVE_DRONE_RENTED)
    public ResponseEntity<?> removeDroneRented(@RequestParam("userId") Integer userId, @RequestParam("droneId") Integer droneId) {
        return new ResponseEntity<>(userService.removeDroneRented(userId, droneId), HttpStatus.ACCEPTED);
    }


}
