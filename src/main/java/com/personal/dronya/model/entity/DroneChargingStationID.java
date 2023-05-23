package com.personal.dronya.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DroneChargingStationID implements Serializable {

    //TODO: este id debe ser primary key para evitar duplicados y se puede poner el mensaje acá.
    @Column(name = "drone_id")
    private Integer droneId;

    @Column(name = "charging_station_id")
    private Integer chargingStationId;
}
