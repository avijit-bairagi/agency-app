package com.avijit.agencyapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity(name = "posts")
@Getter
@Setter
public class PostEntity extends BaseEntity {

    @NotBlank
    @Column(columnDefinition = "TEXT", name = "reason")
    private String status;

    @NotNull
    @Column(name = "post_type")
    private String postType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    public PostEntity() {
        super();
    }

    public PostEntity(@NotBlank String status) {
        super();
        this.status = status;
    }
}