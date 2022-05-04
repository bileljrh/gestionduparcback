package com.gesparc.requests.Maintenance.additionnel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOperationReparationRequest
{
	private Long id;
	
	private String code ;
	
	private String designation;
	
	

}
