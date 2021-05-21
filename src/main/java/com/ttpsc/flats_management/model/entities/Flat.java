package com.ttpsc.flats_management.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Flat {
    @Id
    @GeneratedValue
    private Long id;

    private boolean is_vacant = true;
    private Long rentCost;

    @ManyToOne
    @JsonBackReference
    private Building building;

    @OneToMany
    @JsonManagedReference
    private List<Locator> locators = new ArrayList<>();
}
