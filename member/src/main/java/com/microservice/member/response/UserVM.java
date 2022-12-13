package com.microservice.member.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.microservice.member.response.Roles;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVM {
    private Long id;
    private String name;
    private String firstName;
    private Boolean status;
    private String login;
    private String oldPassword;
    private String password;
    protected Set<Roles> roles;
}
