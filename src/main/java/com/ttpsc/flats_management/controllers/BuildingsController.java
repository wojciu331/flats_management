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

    @GetMapping("/delete")
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

    @GetMapping("/rent-costs")
    public List<Long> getPayments(@RequestParam(value = "buildingId") Long buildingId){
        return this.buildingsService.findPayments(buildingId);
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
