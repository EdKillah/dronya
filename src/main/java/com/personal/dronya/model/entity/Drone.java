package com.personal.dronya.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.personal.dronya.model.enums.DroneStatus;
import com.personal.dronya.model.enums.DroneType;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Drone {

    @Id
    @Column(name = "id",unique=true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private DroneType type;
    private Integer battery;
    private DroneStatus status;

    @ToString.Exclude
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE})
    private UserDrone rentedBy;

    //@ToString.Exclude
    ////@JsonIgnore
    ////@OneToOne(fetch = FetchType.LAZY, cascade={CascadeType.DETACH})
    //private DroneChargingStation station;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return id.equals(drone.id) && Objects.equals(name, drone.name) && type == drone.type && Objects.equals(battery, drone.battery) && status == drone.status && Objects.equals(rentedBy, drone.rentedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, battery, status, rentedBy);
    }
}
