package com.personal.dronya.model.dto.dron;

import com.personal.dronya.model.dto.user.UserDTO;
import com.personal.dronya.model.enums.DroneStatus;
import com.personal.dronya.model.enums.DroneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DroneDTO {

    private Integer id;
    private String name;
    private DroneType type;
    private Integer battery;
    private DroneStatus status;
    private UserDTO rentedBy;
}
