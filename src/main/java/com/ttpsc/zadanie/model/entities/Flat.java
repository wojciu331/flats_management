package com.ttpsc.zadanie.model.entities;

import com.ttpsc.zadanie.enums.FlatStatus;
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
    public Flat(FlatStatus status){
        this.status = status;
    }

    @Id
    @GeneratedValue
    private Long id;

    private FlatStatus status;
    private Long rentCost;

    @JoinColumn
    @ManyToOne
    Building building = new Building();

    @OneToMany
    private List<Locator> locators = new ArrayList<>();
}
