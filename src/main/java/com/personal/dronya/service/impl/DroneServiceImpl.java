package com.personal.dronya.service.impl;

import com.personal.dronya.model.dto.dron.DroneDTO;
import com.personal.dronya.model.dto.user.UserDTO;
import com.personal.dronya.model.entity.*;
import com.personal.dronya.repository.ChargingStationRepository;
import com.personal.dronya.repository.DroneChargingStationRepository;
import com.personal.dronya.repository.DroneRepository;
import com.personal.dronya.service.IDroneService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImpl implements IDroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Autowired
    private DroneChargingStationRepository droneChargingStationRepository;

    @Override
    public Drone saveDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public Drone updateDrone(Drone newDrone) {
        newDrone.setName(Strings.concat(newDrone.getName(), " Updated"));
        return droneRepository.save(newDrone);
    }

    @Override
    public DroneDTO getDrone(Integer id) {
        return droneRepository.findById(id).map(this::mapperDroneToDroneDTO).orElse(null);
    }

    @Override
    public List<DroneDTO> findAllDrones() {
        return droneRepository.findAll().stream().map(this::mapperDroneToDroneDTO).collect(Collectors.toList());
    }

    @Override
    public String registerDroneInChargingStation(Integer droneId, Integer chargingStationId) {
        Optional<Drone> droneOptional = droneRepository.findById(droneId);
        Optional<ChargingStation> chargingStationOptional = chargingStationRepository.findById(chargingStationId);
        if (droneOptional.isEmpty() || chargingStationOptional.isEmpty()) {
            return "Error, droneId or ChargingStationId is not valid.";
        }
        if(droneChargingStationRepository.findDroneChargingStationByDroneId(droneId).isPresent()) {
            return String.format("Drone with id: %s is already registered in another charging station.", droneId);
        }
        Drone droneDB = droneOptional.get();
        ChargingStation chargingStationDB = chargingStationOptional.get();
        DroneChargingStationID droneChargingStationID = DroneChargingStationID
                .builder()
                .chargingStationId(chargingStationId)
                .droneId(droneId)
                .build();
        if (droneChargingStationRepository.findById(droneChargingStationID).isPresent()) {
            return String.format("Drone wit id: %s is already registered in charging station with id: %s", droneId, chargingStationId);
        }
        DroneChargingStation droneChargingStation = DroneChargingStation
                .builder()
                .droneChargingStationID(droneChargingStationID)
                .drone(droneDB)
                .chargingStation(chargingStationDB)
                .build();
        System.out.println("Drone Station: "+droneChargingStation);
        droneChargingStationRepository.save(droneChargingStation);
        return "Drone registered in charging station successfully!";
    }

    @Override
    public String removeDroneInChargingStation(Integer droneId, Integer chargingStationId) {
        //TODO: remover estas lineas y dejarlas en un solo metodo junto con las de registrar dron en charging station.
        Optional<Drone> droneOptional = droneRepository.findById(droneId);
        Optional<ChargingStation> chargingStationOptional = chargingStationRepository.findById(chargingStationId);
        if (droneOptional.isEmpty() || chargingStationOptional.isEmpty()) {
            return "Error, droneId or ChargingStationId is not valid.";
        }
        DroneChargingStationID droneChargingStationID = DroneChargingStationID
                .builder()
                .chargingStationId(chargingStationId)
                .droneId(droneId)
                .build();
        Optional<DroneChargingStation> droneChargingStation = droneChargingStationRepository.findById(droneChargingStationID);
        if (droneChargingStation.isEmpty()) {
            return String.format("Drone wit id: %s is not registered in charging station with id: %s", droneId, chargingStationId);
        }
        droneChargingStationRepository.deleteById(droneChargingStationID);
        return "Drone removed successfully.";
    }

    private DroneDTO mapperDroneToDroneDTO(Drone drone) {
        return DroneDTO.builder()
                .id(drone.getId())
                .name(drone.getName())
                .battery(drone.getBattery())
                .status(drone.getStatus())
                .type(drone.getType())
                //.rentedBy(mapperUserToUserDTO(Optional.ofNullable(drone.getRentedBy()).get().getUser()))
                .build();
    }

    private UserDTO mapperUserToUserDTO(User user) {
        var response = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
        System.out.println("RESPONSE: "+ response);
        return response;
    }


}
