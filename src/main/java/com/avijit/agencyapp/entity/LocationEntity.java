package com.avijit.agencyapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "locations")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;
}
