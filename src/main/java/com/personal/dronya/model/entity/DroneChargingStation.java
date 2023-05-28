package com.personal.dronya.model.entity;

import jakarta.persistence.*;
import java.util.Objects;
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

    @MapsId("droneId")
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.DETACH})
    private Drone drone;

    @MapsId("chargingStationId")
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private ChargingStation chargingStation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneChargingStation that = (DroneChargingStation) o;
        return droneChargingStationID.equals(that.droneChargingStationID) && Objects.equals(drone, that.drone) && Objects.equals(chargingStation, that.chargingStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(droneChargingStationID, drone, chargingStation);
    }
}
