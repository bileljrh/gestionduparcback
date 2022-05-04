package com.gesparc.requests.Carburant;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.gesparc.requests.Administratif.GpsRequest;
import com.gesparc.requests.Administratif.VehiculeRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewAffecttionCarteAgilisCash 
{

    private Long idVehicule;
    
    private LocalDate moisMission;
    
    private String destination;
    
    private int indexDepart;
    
    private int indexArrivee;
    
    private int distanceParcourir;
    
    private float tauxConsommation;
    
    private int montantDemandee;
    
    private int quantiteCarburantReservoir;
    
    private boolean confirmed;
    
    private boolean validated;

   @Override
    public String toString() {
        return "NewAffecttionCarteAgilisCash{" +
                "idVehicule=" + idVehicule +
                ", moisMission=" + moisMission +
                ", destination='" + destination + '\'' +
                ", indexDepart=" + indexDepart +
                ", indexArrivee=" + indexArrivee +
                ", distanceParcourir=" + distanceParcourir +
                ", tauxConsommation=" + tauxConsommation +
                ", montantDemandee=" + montantDemandee +
                ", quantiteCarburantReservoir=" + quantiteCarburantReservoir +
                ", confirmed=" + confirmed +
                ", validated=" + validated +
                '}';
    }
}
