package com.ttpsc.flats_management.controllers;

import com.ttpsc.flats_management.model.entities.Building;
import com.ttpsc.flats_management.services.BuildingsService;
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

    @PostMapping("/create")
    public Building createBuilding(@RequestParam(value = "address") String address,
                                   @RequestParam(value = "zip_code") String zip_code){
        return this.buildingsService.createNewBuilding(new NewBuildingDto(address, zip_code));
    }

    @DeleteMapping("/delete")
    public Building deleteBuilding(@RequestParam(value = "buildingId") Long buildingId){
        return this.buildingsService.deleteBuilding(buildingId);
    }

    @GetMapping("/all")
    public List<Building> allBuildings(){
        return this.buildingsService.findAll();
    }

    @GetMapping("/{id}")
    public Building getSingleBuilding(@PathVariable Long id){
        return this.buildingsService.findOne(id).orElse(null);
    }

    @GetMapping("/{id}-rent-costs")
    public List<Long> getPayments(@PathVariable Long id){
        return this.buildingsService.findPayments(id);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class NewBuildingDto{
        private String address;
        private String zip_code;
    }
}
