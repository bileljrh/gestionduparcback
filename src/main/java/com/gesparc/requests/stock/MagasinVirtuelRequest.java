package com.gesparc.requests.stock;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinVirtuelRequest 
{

	 List<MagasinVirtuelArticleRequest> magasinVirtuelArticle;
	 
	 private Long id;
	 
	 private Date dateTransfert;
	 
	 private String status;
}


