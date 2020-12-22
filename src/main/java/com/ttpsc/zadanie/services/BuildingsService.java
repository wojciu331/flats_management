package com.ttpsc.zadanie.services;

import com.ttpsc.zadanie.controllers.BuildingsController;
import com.ttpsc.zadanie.model.entities.Building;
import com.ttpsc.zadanie.model.entities.Flat;
import com.ttpsc.zadanie.model.repositories.BuildingsRepo;
import com.ttpsc.zadanie.model.repositories.SharedFlatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BuildingsService {
    @Autowired
    private BuildingsRepo buildingsRepo;
    @Autowired
    private SharedFlatsRepo flatsRepo;

    public void createNewBuilding() {
        Building building = new Building();
        buildingsRepo.save(building);
    }

    /*public void deleteBuilding(Long buildingId) {
        List<Building> temp = new ArrayList<>();
        for (int i=1; i<buildingId; i++){
            temp.add(this.buildingsRepo.findById((long) i).get());
        }
        for(int j = (int)(buildingId+1); j<numberOfBuildings; j++){
            temp.add(this.buildingsRepo.findById((long) j).get());
        }
        numberOfBuildings--;
        this.buildingsRepo.deleteAll();
        for(int k=0; k<temp.size(); k++){
            this.buildingsRepo.save(temp.get(k));
        }
    }*/

    public List<Building> findAll() {
        return this.buildingsRepo.findAll();
    }

    public Optional<Building> findOne(Long id) {
        return this.buildingsRepo.findById(id);
    }

    public void addFlatToBuilding(BuildingsController.NewFlatDto flat){
        try{
            Building building = buildingsRepo.findById(flat.getBuildingId()).orElse(null);
            List <Flat> flatList = building.getFlats();
            Flat flatOne = new Flat (flat.getStatus());
            flatOne.setRentCost(flat.getCost());
            flatList.add(flatOne);
            building.setFlats(flatList);
            this.flatsRepo.getRepo().save(flatOne);
            System.out.println("Test");
            this.buildingsRepo.save(building);
            System.out.println("Test");
        } catch (NullPointerException e){
            System.out.println(e);
        }
    }

    public void findPayments(Long id) {
        List<Flat> flats = this.buildingsRepo.findById(id).get().getFlats();
        for (Flat x : flats){
            System.out.println(x.getRentCost());
        }
    }
}
