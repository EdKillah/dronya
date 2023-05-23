package com.personal.dronya.service.impl;

import com.personal.dronya.model.dto.charging_station.ChargingStationRequestDTO;
import com.personal.dronya.model.dto.dron.DroneDTO;
import com.personal.dronya.model.entity.ChargingStation;
import com.personal.dronya.model.entity.Drone;
import com.personal.dronya.repository.ChargingStationRepository;
import com.personal.dronya.service.IChargingStationService;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChargingStationImpl implements IChargingStationService {

    @Autowired
    private ChargingStationRepository chargingStationRepository;

    @Override
    public ChargingStation saveChargingStation(ChargingStation chargingStation) {
        return chargingStationRepository.save(chargingStation);
    }

    @Override
    public ChargingStation updateChargingStation(ChargingStation newChargingStation) {
        newChargingStation.setName(Strings.concat(newChargingStation.getName(), " Updated"));
        return chargingStationRepository.save(newChargingStation);
    }

    @Override
    public ChargingStationRequestDTO getChargingStation(Integer id) {
        return chargingStationRepository.findById(id)
                .map(this::mapperChargingStationToChargingStationDTO)
                .orElse(ChargingStationRequestDTO.builder().name("Charging station not found").build());
    }

    @Override
    public List<ChargingStationRequestDTO> findAllChargingStations() {
        return chargingStationRepository.findAll().stream().map(this::mapperChargingStationToChargingStationDTO).collect(Collectors.toList());
    }

    private ChargingStationRequestDTO mapperChargingStationToChargingStationDTO(ChargingStation chargingStation) {
        return ChargingStationRequestDTO.builder()
                .id(chargingStation.getId())
                .name(chargingStation.getName())
                .description(chargingStation.getDescription())
                .status(chargingStation.getStatus())
                .latitude(chargingStation.getLatitude())
                .longitude(chargingStation.getLongitude())
                .drones(chargingStation.getDrones().stream().map(drone -> mapperDroneToDroneDTO(drone.getDrone())).collect(Collectors.toList()))
                .build();
    }

    private DroneDTO mapperDroneToDroneDTO(Drone drone) {
        return DroneDTO.builder()
                .id(drone.getId())
                .name(drone.getName())
                .status(drone.getStatus())
                .battery(drone.getBattery())
                .type(drone.getType())
                .build();
    }

}
