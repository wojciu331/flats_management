package com.ttpsc.flats_management.services;

import com.ttpsc.flats_management.controllers.BuildingsController;
import com.ttpsc.flats_management.controllers.FlatsController;
import com.ttpsc.flats_management.controllers.LocatorsController;
import com.ttpsc.flats_management.model.entities.Building;
import com.ttpsc.flats_management.model.entities.Flat;
import com.ttpsc.flats_management.model.entities.Locator;
import com.ttpsc.flats_management.services.BuildingsService;
import com.ttpsc.flats_management.services.FlatsService;
import com.ttpsc.flats_management.services.LocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionsService {
    @Autowired private BuildingsService buildingsService;
    @Autowired private FlatsService flatsService;
    @Autowired private LocatorService locatorService;

    public Building convert(BuildingsController.NewBuildingDto building_dto){
        Building building = new Building();
        building.setAddress(building_dto.getAddress());
        building.setZip_code(building_dto.getZip_code());
        return building;
    }

    public Flat convert(FlatsController.NewFlatDto flat_dto) {
        Flat flat = new Flat();
        flat.set_vacant(flat_dto.is_vacant());
        flat.setRentCost(flat_dto.getCost());
        return flat;
    }

    public Locator convert(LocatorsController.NewLocatorDto locator_dto) {
        Locator locator = new Locator();
        locator.setMail(locator_dto.getMail());
        locator.setName(locator_dto.getName());
        locator.setSurname(locator_dto.getSurname());
        locator.setPaid(locator_dto.getPaid());
        return locator;
    }
}
