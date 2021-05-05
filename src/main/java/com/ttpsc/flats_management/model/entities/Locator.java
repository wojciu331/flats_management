package com.ttpsc.flats_management.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ttpsc.flats_management.model.entities.Flat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Locator {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private String mail;
    private Boolean paid = false;

    @JoinColumn
    @ManyToOne
    @JsonBackReference
    Flat flat = new Flat();
}
