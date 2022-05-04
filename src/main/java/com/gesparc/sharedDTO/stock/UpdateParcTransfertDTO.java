package com.gesparc.sharedDTO.stock;

import java.util.Date;
import java.util.List;

import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelArticleDTO;
import com.gesparc.sharedDTO.stock.additionnel.UpdateMagasinVirtuelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UpdateParcTransfertDTO {
	
List<UpdateParcTransfertArticleDTO> updateParcTransfertArticle;
	
    private Long id;
	
	private Date dateTransfert;
    
	private String status;

}
