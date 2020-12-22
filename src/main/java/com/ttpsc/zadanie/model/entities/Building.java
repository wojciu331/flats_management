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
public class Building {
    @Id
    @GeneratedValue
    private Long id;

    public List<Flat> getFlats() {
        return flats;
    }

    @OneToMany
    private List<Flat> flats = new ArrayList<>();
}
