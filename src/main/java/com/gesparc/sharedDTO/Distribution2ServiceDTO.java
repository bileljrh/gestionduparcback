package com.gesparc.sharedDTO;

import java.time.LocalDate;

import com.gesparc.sharedDTO.administratif.VehiculeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distribution2ServiceDTO 
{
    EtatMensuelDTO etatMensuel;
    
    private Long id;
    
    private String typeDistributionService;

     @Override
    public String toString() {
        return "Distribution2ServiceDTO{" +
                "id=" + id +
                ", typeDistributionService='" + typeDistributionService + '\'' +
                ", etatMensuel=" + etatMensuel +
                '}';
    }
}
