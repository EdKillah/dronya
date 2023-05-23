package com.personal.dronya.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.dronya.model.enums.ChargeStationStatus;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ChargingStation {
    @Id
    @Column(name = "id", unique=true, nullable = false, columnDefinition = "char(36)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String description;
    private Double longitude;
    private Double latitude;
    private ChargeStationStatus status;
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "chargingStation")
    private List<DroneChargingStation> drones;
}
