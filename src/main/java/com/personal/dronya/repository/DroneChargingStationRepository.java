package com.personal.dronya.repository;

import com.personal.dronya.model.entity.DroneChargingStation;
import com.personal.dronya.model.entity.DroneChargingStationID;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneChargingStationRepository extends JpaRepository<DroneChargingStation, DroneChargingStationID> {

    @Query("SELECT drone_station FROM DroneChargingStation drone_station WHERE drone_station.droneChargingStationID.droneId = ?1")
    Optional<DroneChargingStation> findDroneChargingStationByDroneId(Integer droneId);
}
