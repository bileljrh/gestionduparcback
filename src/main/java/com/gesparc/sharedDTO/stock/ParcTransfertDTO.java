package com.gesparc.sharedDTO.stock;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.gesparc.entities.stock.ParcTransfertArticleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcTransfertDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	List<ParcTransfertArticleEntity> parcTransfertArticle;

	private Long id;
	
	private Date dateTransfert;
    
	private String status;
	
    private Boolean confirmed  ;
    
    private Boolean validated ;
}
