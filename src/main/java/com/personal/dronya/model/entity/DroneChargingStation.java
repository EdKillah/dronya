package com.personal.dronya.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder @Data
@Table(name = "drone_charging_station")
public class DroneChargingStation {

    @EmbeddedId
    private DroneChargingStationID droneChargingStationID;

    @MapsId("drone_id")
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.DETACH})
    private Drone drone;

    @MapsId("charging_station_id")
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private ChargingStation chargingStation;
}
