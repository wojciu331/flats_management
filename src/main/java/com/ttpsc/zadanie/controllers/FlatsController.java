package com.ttpsc.zadanie.controllers;

import com.ttpsc.zadanie.model.entities.Flat;
import com.ttpsc.zadanie.services.FlatsService;
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

    @PostMapping("/delete-flat")
    public void deleteFlat(@RequestParam(value = "flatId") Long flatId) {
        //this.flatsService.deleteFlat(flatId);
    }

    @GetMapping("/all-flats")
    public List<Flat> allFlats(){
        return this.flatsService.findAll();
    }

    @GetMapping("/available-flats")
    public List<Flat> availableFlats(){
        return this.flatsService.findAvailable();
    }

    @GetMapping("/{id}")
    public Flat getSingleFlat(@PathVariable Long id){
        return this.flatsService.findOne(id).orElse(null);
    }

    @PostMapping("/add-locator")
    public void AddNewLocatorToFlat (@RequestParam(value = "flatId") Long flatId,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "surname") String surname,
                                     @RequestParam(value = "mail") String mail){
        this.flatsService.AddLocatorToFlat(new NewLocatorDto(flatId, name, surname, mail, false));
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class NewLocatorDto{
        Long flatId;
        String name;
        String surname;
        String mail;
        Boolean paid;
    }
}
