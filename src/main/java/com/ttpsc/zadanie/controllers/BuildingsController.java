package com.ttpsc.zadanie.controllers;

import com.ttpsc.zadanie.enums.FlatStatus;
import com.ttpsc.zadanie.model.entities.Building;
import com.ttpsc.zadanie.services.BuildingsService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/buildings")
public class BuildingsController {
    @Autowired
    private BuildingsService buildingsService;

    @PostMapping("/create-building")
    public void createBuilding(){
        this.buildingsService.createNewBuilding();
    }

    @PostMapping("/delete-building")
    public void deleteBuilding(@RequestParam(value = "buildingId") Long buildingId){
        //this.buildingsService.deleteBuilding(buildingId);
    }

    @GetMapping("/all-buildings")
    public List<Building> allBuildings(){
        return this.buildingsService.findAll();
    }

    @GetMapping("/{id}")
    public Building getSingleBuilding(@PathVariable Long id){
        return this.buildingsService.findOne(id).orElse(null);
    }

    @GetMapping("/{id}-rent-costs")
    public void getPayments(@PathVariable Long id){
        this.buildingsService.findPayments(id);
    }

    @PostMapping("/add-flat")
    public void addNewFlatToBuilding(@RequestParam(value = "buildingId") Long buildingId,
                                     @RequestParam(value = "cost") Long cost){
        this.buildingsService.addFlatToBuilding(new NewFlatDto(buildingId, FlatStatus.VACANT, cost));
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class NewFlatDto{
        Long buildingId;
        FlatStatus status;
        Long cost;
    }
}
