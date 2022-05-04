package com.gesparc.entities.administration;

import com.gesparc.entities.AbstractBaseEntity
;
import com.gesparc.security.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.security.entity.UserEntity;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "message")
public class MessageEntity extends  AbstractBaseEntity
{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    
    private UserEntity user;
    
    private Date dateSent;
    
    private Boolean responded;
}
