package com.gesparc.entities.stock;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parc_transfert")
public class ParcTransfertEntity extends  AbstractBaseEntity {
	
	@Id
	@GeneratedValue
	private Long id ;
	
    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateTransfert;
    
    private String status;
    
    private Boolean confirmed  ;
    
    private Boolean validated ;
    
    @OneToMany(mappedBy = "parcTransfert", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<ParcTransfertArticleEntity> parcTransfertArticle;

}
