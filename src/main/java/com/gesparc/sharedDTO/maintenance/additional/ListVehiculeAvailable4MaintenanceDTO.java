package com.gesparc.sharedDTO.maintenance.additional;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.gesparc.sharedDTO.exploitation.ListVehiculeDataByStructureDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListVehiculeAvailable4MaintenanceDTO 
{
    private String structure;
    
    private List<ListVehiculeDataByStructureDTO> vehiculesData;
}
