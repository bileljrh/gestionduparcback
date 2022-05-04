package com.gesparc.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.gesparc.entities.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.gesparc.entities.AbstractBaseEntity;
import com.gesparc.entities.administration.MessageEntity;
import com.gesparc.entities.administration.TracabiliteEntity;
import com.gesparc.entities.carburant.carteJocker.DeclarationPerteCarteJockerEntity;
import com.gesparc.entities.carburant.cartePlafond.DeclarationPerteCartePlafondEntity;
import com.gesparc.entities.referentiel.MagasinEntity;
import com.gesparc.entities.referentiel.StructureEntity;
import com.gesparc.entities.referentiel.UgpEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


public class UserPrincipalEntity implements UserDetails 
{

    private final UserEntity user;

    public UserPrincipalEntity(UserEntity user) 
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        HashSet<GrantedAuthority> authorities = new HashSet<>(user.roles.size());
        for (RoleEntity role : user.roles) 
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getDesignation()));
        }
        return authorities;
    }

    @Override
    public String getPassword() 
    {
        return this.user.getMot2passe();
    }

    @Override
    public String getUsername() 
    {
        return this.user.getMatricule();
    }


    @Override
    public boolean isAccountNonExpired() 
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() 
    {
        return this.user.isNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() 
    {
        return true;
    }

    @Override
    public boolean isEnabled() 
    {
        return this.user.isActive();
    }


}
