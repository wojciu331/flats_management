package com.ttpsc.zadanie.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Locator {
    @Id
    @GeneratedValue
    private Long id;

    public Locator(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    private String name;
    private String surname;
    private String mail;
    private Boolean paid;

    @JoinColumn
    @ManyToOne
    Flat flat = new Flat();
}
