package com.ttpsc.zadanie.controllers;

import com.itextpdf.text.DocumentException;
import com.ttpsc.zadanie.model.entities.Locator;
import com.ttpsc.zadanie.services.LocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/locators")
public class LocatorsController {
    @Autowired
    private LocatorService locatorService;

    @PostMapping("/delete-locator")
    public void deleteLocator(@RequestParam(value = "locatorId") Long locatorId){
        //this.locatorService.deleteLocator(locatorId);
    }

    @PostMapping("/invoice")
    public void create_invoice(@RequestParam(value="locatorId") Long locatorId) throws FileNotFoundException, DocumentException {
        String name = getSingleLocator(locatorId).getName();
        String surname = getSingleLocator(locatorId).getSurname();
        String email = getSingleLocator(locatorId).getMail();
        Long rentCost = getSingleLocator(locatorId).getFlat().getRentCost();
        this.locatorService.create_invoice(locatorId, name,surname, email, rentCost);
        /*dla testów:
        this.locatorService.create_invoice("a", "b", "c", 1500L);*/
    }

    @GetMapping("/{id}")
    public Locator getSingleLocator(@PathVariable Long id){
        return this.locatorService.findOne(id).orElse(null);
    }
}
