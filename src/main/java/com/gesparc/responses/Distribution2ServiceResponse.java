package com.gesparc.responses;


import com.gesparc.responses.administratif.EtatMensuelResponse;
import java.time.LocalDate;

import com.gesparc.responses.administratif.EtatMensuelResponse;
import com.gesparc.responses.administratif.VehiculeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distribution2ServiceResponse 
{
    EtatMensuelResponse etatMensuel;
    
    private Long id;
    
    private String typeDistributionService;
    
    @Override
    public String toString() {
        return "Distribution2ServiceResponse{" +
                "id=" + id +
                ", typeDistributionService='" + typeDistributionService + '\'' +
                ", etatMensuel=" + etatMensuel +
                '}';
    }
}
