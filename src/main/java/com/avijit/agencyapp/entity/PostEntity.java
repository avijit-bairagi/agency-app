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
    private String description;

    @NotNull
    @Column(name = "post_type")
    private boolean postType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity locationEntity;

    @JsonIgnore
    @ManyToMany(mappedBy = "posts")
    private Collection<UserEntity> users;

    public PostEntity() {
        super();
    }

    public PostEntity(final String description) {
        super();
        this.description = description;
    }
}