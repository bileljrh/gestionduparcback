package com.gesparc.responses.carburant;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListCarteAgilisCashResponse 
{
    private Long idCarte;
    
    private String numeroCarte;
    
    private String typeCarburant;
    
    private LocalDate dateDebutUtilisation;
    
    private int soldeRestant;

    @Override
    public String toString() {
        return "ListCarteAgilisCashResponse{" +
                "idCarte=" + idCarte +
                ", numeroCarte='" + numeroCarte + '\'' +
                ", typeCarburant='" + typeCarburant + '\'' +
                ", dateDebutUtilisation=" + dateDebutUtilisation +
                ", soldeRestant=" + soldeRestant +
                '}';
    }
}
