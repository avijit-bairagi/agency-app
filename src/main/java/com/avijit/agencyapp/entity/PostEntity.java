package com.avijit.agencyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "posts")
@Getter
@Setter
@AllArgsConstructor
public class PostEntity extends BaseEntity {

    @NotBlank
    @Column(columnDefinition = "TEXT", name = "reason")
    private String status;

    @NotNull
    @Column(name = "privacy_type")
    private String privacyType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public PostEntity() {
        super();
    }

    public PostEntity(@NotBlank String status) {
        super();
        this.status = status;
    }
}