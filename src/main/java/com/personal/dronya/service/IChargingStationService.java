package com.personal.dronya.service;

import com.personal.dronya.model.dto.charging_station.ChargingStationRequestDTO;
import com.personal.dronya.model.entity.ChargingStation;

import java.util.List;

public interface IChargingStationService {

    ChargingStation saveChargingStation(ChargingStation chargingStation);
    ChargingStation updateChargingStation(ChargingStation newChargingStation);
    ChargingStationRequestDTO getChargingStation(Integer id);
    List<ChargingStationRequestDTO> findAllChargingStations();
}
