package com.gesparc.sharedDTO.stock;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagasinVirtuelDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	List<MagasinVirtuelArticleDTO> magasinVirtuelArticle;

	private Long id;
	
	private Date dateTransfert;
    
	private String status;
}


