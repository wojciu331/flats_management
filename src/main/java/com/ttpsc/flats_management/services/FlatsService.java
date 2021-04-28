package com.ttpsc.flats_management.services;

import com.ttpsc.flats_management.controllers.FlatsController;
import com.ttpsc.flats_management.model.entities.Building;
import com.ttpsc.flats_management.model.entities.Flat;
import com.ttpsc.flats_management.model.repositories.BuildingsRepo;
import com.ttpsc.flats_management.model.repositories.FlatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FlatsService {
    @Autowired
    private FlatsRepo flatsRepo;
    @Autowired
    private BuildingsRepo buildingsRepo;
    @Autowired
    private ConversionsService conversionsService;

    public Flat addFlatToBuilding(FlatsController.NewFlatDto flat){
        Flat flatOne = conversionsService.convert(flat);
        Building building = buildingsRepo.findById(flat.getBuildingId()).orElse(null);
        this.buildingsRepo.findById(flat.getBuildingId()).ifPresent(flatOne::setBuilding);
        List <Flat> flatList = building.getFlats();
        flatList.add(flatOne);
        building.setFlats(flatList);
        this.flatsRepo.save(flatOne);
        this.buildingsRepo.save(building);
        return flatOne;
    }

    public Flat deleteFlat(Long flatId) {
        Flat flat = this.flatsRepo.findById(flatId).orElse(null);
        this.flatsRepo.deleteById(flatId);
        return flat;
    }

    public List<Flat> findAll() {
        return this.flatsRepo.findAll();
    }

    public Optional<Flat> findOne(Long id) {
        return this.flatsRepo.findById(id);
    }

    public List<Flat> findAvailable() {
        List<Flat> listOfAll = this.flatsRepo.findAll();
        List<Flat> listOfAvailable = new ArrayList<>();
        for (Flat x : listOfAll){
            if (x.is_vacant())
                listOfAvailable.add(x);
        }
        return listOfAvailable;
    }
}
