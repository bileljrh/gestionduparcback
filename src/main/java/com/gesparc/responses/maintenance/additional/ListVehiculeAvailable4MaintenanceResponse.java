package com.gesparc.responses.maintenance.additional;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.gesparc.responses.exploitation.additional.ListVehiculeDataByStructureResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListVehiculeAvailable4MaintenanceResponse 
{
    private String structure;
    
    private List<ListVehiculeDataByStructureResponse> vehiculesData;
}
