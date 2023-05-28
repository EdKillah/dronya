package com.personal.dronya.model.entity;

import jakarta.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_with_drones")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserDrone {

    @EmbeddedId
    private UserDroneID userDroneId;

    @MapsId("userId")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private User user;


    @MapsId("droneId")
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.DETACH})
    private Drone drone;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDrone userDrone = (UserDrone) o;
        return Objects.equals(userDroneId, userDrone.userDroneId) && Objects.equals(user, userDrone.user) && Objects.equals(drone, userDrone.drone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDroneId, user, drone);
    }
}
