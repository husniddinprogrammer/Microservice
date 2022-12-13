package com.microservice.admin.service.dto;

import com.microservice.admin.entity.Roles;
import com.microservice.admin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String firstName;
    private Boolean status;
    private String login;
    protected Set<Roles> roles;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.firstName = user.getFirstName();
        this.status = user.getStatus();
        this.login = user.getLogin();
        this.roles = user.getRoles();
    }

}

