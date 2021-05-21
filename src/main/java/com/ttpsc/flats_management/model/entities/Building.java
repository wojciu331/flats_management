package com.ttpsc.flats_management.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private String address;
    private String zip_code;

    @OneToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonManagedReference
    private List<Flat> flats = new ArrayList<>();
}
