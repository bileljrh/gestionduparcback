package com.gesparc.sharedDTO.stock;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListRetourDTO 
{
	 @Id
	 @GeneratedValue
	 private Long id;
	
	 private Date dateSortie; 
	 
	 private String magasin ; 
	 
	 private String structure ; 
	
}
