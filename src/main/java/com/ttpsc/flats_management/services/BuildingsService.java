package com.ttpsc.flats_management.services;

import com.ttpsc.flats_management.controllers.BuildingsController;
import com.ttpsc.flats_management.model.entities.Building;
import com.ttpsc.flats_management.model.entities.Flat;
import com.ttpsc.flats_management.model.repositories.BuildingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BuildingsService {
    @Autowired
    private BuildingsRepo buildingsRepo;
    @Autowired
    private ConversionsService conversionsService;

    public Building createNewBuilding(BuildingsController.NewBuildingDto building_dto) {
        Building building = conversionsService.convert(building_dto);
        buildingsRepo.save(building);
        return building;
    }

    public Building deleteBuilding(Long buildingId) {
        Building building = this.buildingsRepo.findById(buildingId).orElse(null);
       if(building != null) this.buildingsRepo.deleteById(buildingId);
       return building;
    }

    public List<Building> findAll() {
        return this.buildingsRepo.findAll();
    }

    public Optional<Building> findOne(Long id) {
        return this.buildingsRepo.findById(id);
    }

    public List<Long> findPayments(Long id) {
        List<Flat> flats = this.buildingsRepo.findById(id).get().getFlats();
        List<Long> payments = new ArrayList<>();
        for (Flat x : flats){
            payments.add(x.getRentCost());
        }
        return payments;
    }
}
