package com.gesparc.entities.administration.additionnel;

import com.gesparc.entities.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyMot2PassRequest extends  AbstractBaseEntity
{
    private Long id;
    
    private String nouveauMot2pass;
    
    private String ancienMot2pass;
    
}
