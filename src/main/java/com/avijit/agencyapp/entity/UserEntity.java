package com.avijit.agencyapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@ToString
public class UserEntity extends BaseEntity {

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(unique = true, name = "email")
    private String email;

    @JsonIgnore
    @NotBlank
    @Column(unique = true, name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_posts",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "post_id", referencedColumnName = "id"))
    private Set<PostEntity> posts;
}