package com.personal.dronya.model.dto.user_dron;

import com.personal.dronya.model.dto.dron.DroneDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserDroneDTO {
    private DroneDTO droneDTO;
}
