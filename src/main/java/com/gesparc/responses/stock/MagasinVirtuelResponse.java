package com.gesparc.responses.stock;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinVirtuelResponse 
{
	private Long id ;
	
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateTransfert;
    
    private String status;

}
