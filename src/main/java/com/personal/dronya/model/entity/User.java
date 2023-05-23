package com.personal.dronya.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @Column(name = "id",unique=true, nullable = false, columnDefinition = "varchar(36)")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserDrone> droneRented;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(droneRented, user.droneRented);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email, droneRented);
    }
}
