package com.gesparc.requests;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.gesparc.requests.Administratif.BeneficiaireRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distribution2ServiceRequest 
{
    EtatMensuelRequest etatMensuel;
    
    private Long id;
    
    private String typeDistributionService;

    @Override
    public String toString() {
        return "Distribution2ServiceReauest{" +
                "id=" + id +
                ", typeDistributionService='" + typeDistributionService + '\'' +
                ", etatMensuel=" + etatMensuel +
                '}';
    }
}
