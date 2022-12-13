package com.microservice.admin.security;

import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class SecurityUtil {
    public static String getCurrentUserLogin(){
        Principal p = SecurityContextHolder.getContext().getAuthentication();
        if(p != null && p.getName() != null){
            return p.getName();
        }
        return null;
    }
}
