package com.gesparc.requests.referentiel;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FamilleArticleRequest 
{
    
    private Long id;
    
    private String famille;
    
    private String code;
    }
