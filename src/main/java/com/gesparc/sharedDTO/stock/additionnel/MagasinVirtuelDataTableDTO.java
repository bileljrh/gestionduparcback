package com.gesparc.sharedDTO.stock.additionnel;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gesparc.sharedDTO.stock.MagasinVirtuelArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinVirtuelDataTableDTO 
{
	private Long id ;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateTransfert;
    
	private String status;
    
	private Boolean confirmed ;
    
	private Boolean validated ;
}
