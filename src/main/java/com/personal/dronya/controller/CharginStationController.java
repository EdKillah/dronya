package com.personal.dronya.controller;

import com.personal.dronya.model.entity.ChargingStation;
import com.personal.dronya.service.IChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndpointConstants.CHARGING_STATION_BASE_URL)
public class CharginStationController {

    @Autowired
    private IChargingStationService chargingStationService;

    @GetMapping(EndpointConstants.CHARGING_STATION_GET)
    public ResponseEntity<?> getChargingStation(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(chargingStationService.getChargingStation(id), HttpStatus.OK);
    }

    @GetMapping(EndpointConstants.CHARGING_STATION_GET_ALL)
    public ResponseEntity<?> getAllChargingStations() {
        return new ResponseEntity<>(chargingStationService.findAllChargingStations(), HttpStatus.OK);
    }

    @PostMapping(EndpointConstants.CHARGING_STATION_SAVE)
    public ResponseEntity<?> saveChargingStation(@RequestBody ChargingStation chargingStation) {
        return new ResponseEntity<>(chargingStationService.saveChargingStation(chargingStation), HttpStatus.ACCEPTED);
    }

}
