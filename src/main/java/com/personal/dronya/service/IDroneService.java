package com.personal.dronya.service;

import com.personal.dronya.model.dto.dron.DroneDTO;
import com.personal.dronya.model.entity.Drone;
import java.util.List;

public interface IDroneService {

    Drone saveDrone(Drone drone);
    Drone updateDrone(Drone newDrone);
    DroneDTO getDrone(Integer id);
    List<DroneDTO> findAllDrones();

    String registerDroneInChargingStation(Integer droneId, Integer chargingStationId);

    String removeDroneInChargingStation(Integer droneId, Integer chargingStationId);
}
