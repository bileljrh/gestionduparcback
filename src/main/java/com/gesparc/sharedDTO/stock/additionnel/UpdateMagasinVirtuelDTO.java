package com.gesparc.sharedDTO.stock.additionnel;

import java.util.Date;
import java.util.List;

import com.gesparc.sharedDTO.stock.MagasinVirtuelArticleDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMagasinVirtuelDTO 
{
    List<UpdateMagasinVirtuelArticleDTO> updateMagasinVirtuelArticle;
	
    private Long id;
	
	private Date dateTransfert;
    
	private String status;
}
