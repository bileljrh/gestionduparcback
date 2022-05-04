package com.gesparc.requests.Administratif.Additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationVehiculeTableDataRequest implements Serializable 
{
    private Long id;
    
    private Long idVehicule;
    
    private String numeroPlaque;
    
    private String codeStructure;
    
    private String designationStructure;
    
    private LocalDate dateDemandeReservation;
    
    private LocalDate dateConfirmationDemandeReservation;
    
    private LocalDate dateDebutReservation;
    
    private LocalDate dateFinReservation;
    
    private String objet;
    
    private boolean confirmed;
    
}
