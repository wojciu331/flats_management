package com.ttpsc.flats_management.controllers;

import com.ttpsc.flats_management.model.entities.Flat;
import com.ttpsc.flats_management.services.FlatsService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/flats")
public class FlatsController {
    @Autowired
    private FlatsService flatsService;

    @PostMapping("/add")
    public Flat addNewFlatToBuilding(@RequestParam(value = "buildingId") Long buildingId,
                                     @RequestParam(value = "cost") Long cost){
        return this.flatsService.addFlatToBuilding(new NewFlatDto(buildingId, true, cost));
    }

    @GetMapping("/delete")
    public Flat deleteFlat(@RequestParam(value = "flatId") Long flatId) {
        return this.flatsService.deleteFlat(flatId);
    }

    @GetMapping("/all")
    public List<Flat> allFlats(){
        return this.flatsService.findAll();
    }

    @GetMapping("/available")
    public List<Flat> availableFlats(){
        return this.flatsService.findAvailable();
    }

    @GetMapping("/{id}")
    public Flat getSingleFlat(@PathVariable Long id){
        return this.flatsService.findOne(id).orElse(null);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class NewFlatDto{
        private Long buildingId;
        private boolean is_vacant;
        private Long cost;
    }
}
