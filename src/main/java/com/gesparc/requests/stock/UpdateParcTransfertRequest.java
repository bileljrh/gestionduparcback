package com.gesparc.requests.stock;

import java.util.Date;
import java.util.List;

import com.gesparc.requests.stock.additionnel.UpdateMagasinVirtuelArticleRequest;
import com.gesparc.requests.stock.additionnel.UpdateMagasinVirtuelRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateParcTransfertRequest {
	
	List<UpdateParcTransfertArticleRequest> updateParcTransfertArticle;
	 
	 private Long id;
	 
	 private Date dateTransfert;
	 
	 private String status;

}
