package com.gesparc.requests.stock.additionnel;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMagasinVirtuelRequest 
{

	 List<UpdateMagasinVirtuelArticleRequest> updateMagasinVirtuelArticle;
	 
	 private Long id;
	 
	 private Date dateTransfert;
	 
	 private String status;
}


