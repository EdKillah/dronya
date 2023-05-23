package com.personal.dronya.model.dto.user;

import com.personal.dronya.model.dto.user_dron.UserDroneDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDTO {

    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private List<UserDroneDTO> droneRented;
}
