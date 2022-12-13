package com.microservice.admin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservice.admin.config.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,length = 20)
    private String name;
    @Column(unique = true,length = 30)
    private String firstName;
    private Boolean status;
    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 4, max = 30)
    @Column(length = 30, unique = true, nullable = false)
    private String login;
    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;
    @JsonIgnore
    @ElementCollection(targetClass = Roles.class)
    @CollectionTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role_id")
    protected Set<Roles> roles;
}
