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
@AllArgsConstructor @NoArgsConstructor @Builder
public class UserDroneID implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "drone_id")
    private Integer droneId;
}
