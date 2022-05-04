
package com.gesparc.sharedDTO.referentiel;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gesparc.sharedDTO.Achat.BonCommandeDTO;
import com.gesparc.sharedDTO.administratif.VehiculeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurListDTO implements Serializable 
{
	private Long id;
	
	private String code;
	
	private String designation;
}