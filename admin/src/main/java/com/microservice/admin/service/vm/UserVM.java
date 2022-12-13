package com.microservice.admin.service.vm;

import com.microservice.admin.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserVM {
    private Long id;
    private String name;
    private String firstName;
    private Boolean status;
    private String login;
    private String oldPassword;
    private String password;
    protected Set<Roles> roles;

    public UserVM(Long id, String name, String firstName, Boolean status, String login, String password, Set<Roles> roles) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.status = status;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }
}
