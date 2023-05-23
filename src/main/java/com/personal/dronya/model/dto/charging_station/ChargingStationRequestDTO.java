package com.personal.dronya.model.dto.charging_station;

import com.personal.dronya.model.dto.dron.DroneDTO;
import com.personal.dronya.model.enums.ChargeStationStatus;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ChargingStationRequestDTO {
    private Integer id;
    private String name;
    private String description;
    private Double longitude;
    private Double latitude;
    private ChargeStationStatus status;
    private List<DroneDTO> drones;
}
