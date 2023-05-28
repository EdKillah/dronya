package com.personal.dronya.controller;

import com.personal.dronya.model.entity.Drone;
import com.personal.dronya.service.IDroneService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndpointConstants.DRONE_BASE_URL)
public class DroneController {

    @Autowired
    private IDroneService droneService;

    @GetMapping(EndpointConstants.DRONE_GET)
    public ResponseEntity<?> getDrone(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(droneService.getDrone(id), HttpStatus.OK);
    }

    @GetMapping(EndpointConstants.DRONE_GET_ALL)
    public ResponseEntity<?> getAllDrones() {
        return new ResponseEntity<>(droneService.findAllDrones(), HttpStatus.OK);
    }

    @PostMapping(EndpointConstants.DRONE_SAVE)
    public ResponseEntity<?> saveDrone(@RequestBody Drone drone) {
        return new ResponseEntity<>(persistDrone(drone), HttpStatus.ACCEPTED);
    }

    @GetMapping(EndpointConstants.REGISTER_DRONE_IN_CHARGING_STATION)
    public ResponseEntity<?> registerDroneInChargingStation(@RequestParam("droneId") Integer droneId, @RequestParam("chargingStationId") Integer chargingStationId) {
        return new ResponseEntity<>(droneService.registerDroneInChargingStation(droneId, chargingStationId), HttpStatus.OK);
    }

    @DeleteMapping(EndpointConstants.REMOVE_DRONE_IN_CHARGING_STATION)
    public ResponseEntity<?> removeDroneInChargingStation(@RequestParam("droneId") Integer droneId, @RequestParam("chargingStationId") Integer chargingStationId) {
        return new ResponseEntity<>(droneService.removeDroneInChargingStation(droneId, chargingStationId), HttpStatus.OK);
    }

    private Drone persistDrone(Drone drone) {
        return Objects.isNull(drone.getRentedBy()) ? droneService.saveDrone(drone) : droneService.updateDrone(drone);
    }
}
