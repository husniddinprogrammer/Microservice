package com.microservice.admin.security;


import com.microservice.admin.entity.Roles;
import com.microservice.admin.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * User special for Spring Security
 * */
public class UserMaxsus implements UserDetails  {
   private String username;
   private String password;
   private boolean rememberMe;


   private Boolean enabled = false;
   private Collection<SimpleGrantedAuthority> authorities;

   public UserMaxsus(){}
   public UserMaxsus(User u) {
        this.username = u.getLogin();
        this.password = u.getPassword();
        this.enabled = u.getStatus();
   }

   @Override
   public String getUsername() {
        return username;
    }

   @Override
   public boolean isAccountNonExpired() {
        return true;
    }

   @Override
   public boolean isAccountNonLocked() {
        return true;
    }

   @Override
   public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
    public void setLavozimlar(Set<Roles> lavozimlar){
        this.authorities = new HashSet<SimpleGrantedAuthority>();
        lavozimlar.forEach(l -> authorities.add(new SimpleGrantedAuthority(l.name())));
    }
}
