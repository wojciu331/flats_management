package com.ttpsc.flats_management.controllers;

import com.itextpdf.text.DocumentException;
import com.ttpsc.flats_management.model.entities.Locator;
import com.ttpsc.flats_management.services.LocatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/locators")
public class LocatorsController {
    @Autowired
    private LocatorService locatorService;

    @PostMapping("/add")
    public Locator AddNewLocatorToFlat (@RequestParam(value = "flatId") Long flatId,
                                     @RequestParam(value = "name") String name,
                                     @RequestParam(value = "surname") String surname,
                                     @RequestParam(value = "mail") String mail){
        return this.locatorService.AddLocatorToFlat(new NewLocatorDto(flatId, name, surname, mail, false));
    }

    @DeleteMapping("/delete")
    public Locator deleteLocator(@RequestParam(value = "locatorId") Long locatorId){
        return this.locatorService.deleteLocator(locatorId);
    }

    @PostMapping("/invoice")
    public void create_invoice(@RequestParam(value="locatorId") Long locatorId) throws FileNotFoundException, DocumentException {
        String name = getSingleLocator(locatorId).getName();
        String surname = getSingleLocator(locatorId).getSurname();
        String email = getSingleLocator(locatorId).getMail();
        Long rentCost = getSingleLocator(locatorId).getFlat().getRentCost();
        this.locatorService.create_invoice(locatorId, name,surname, email, rentCost);
    }

    @GetMapping("/{id}")
    public Locator getSingleLocator(@PathVariable Long id){
        return this.locatorService.findOne(id).orElse(null);
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
