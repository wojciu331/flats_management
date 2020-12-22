package com.ttpsc.zadanie.services;

import com.ttpsc.zadanie.controllers.FlatsController;
import com.ttpsc.zadanie.enums.FlatStatus;
import com.ttpsc.zadanie.model.entities.Flat;
import com.ttpsc.zadanie.model.entities.Locator;
import com.ttpsc.zadanie.model.repositories.SharedFlatsRepo;
import com.ttpsc.zadanie.model.repositories.SharedLocatorsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FlatsService {
    @Autowired
    SharedLocatorsRepo locatorsRepo;
    @Autowired
    SharedFlatsRepo flatsRepo;

    /*public void deleteFlat(Long flatId) {
        List<Flat> temp = new ArrayList<>();
        for (int i=1; i<flatId; i++){
            temp.add(this.flatsRepo.getRepo().findById((long) i).get());
        }
        int max = BuildingsService.getNumberOfFlats();
        for(int j = (int)(flatId+1); j<max; j++){
            temp.add(this.flatsRepo.getRepo().findById((long) j).get());
        }
        BuildingsService.setNumberOfFlats(max-1);
        for(int k=0; k<temp.size(); k++){
            this.flatsRepo.getRepo().save(temp.get(k));
        }
    }*/

    public void AddLocatorToFlat(FlatsController.NewLocatorDto locator) {
        try{
            Flat flat = flatsRepo.getRepo().findById(locator.getFlatId()).orElse(null);
            List<Locator> locatorList = flat.getLocators();
            Locator locatorOne = new Locator (locator.getName(), locator.getSurname());
            locatorOne.setMail(locator.getMail());
            locatorList.add(locatorOne);
            flat.setLocators(locatorList);
            flat.setStatus(FlatStatus.OCCUPIED);
            this.locatorsRepo.getRepo().save(locatorOne);
            this.flatsRepo.getRepo().save(flat);
        } catch (NullPointerException e){
            System.out.println(e);
        }
    }

    public List<Flat> findAll() {
        return this.flatsRepo.getRepo().findAll();
    }

    public Optional<Flat> findOne(Long id) {
        return this.flatsRepo.getRepo().findById(id);
    }

    public List<Flat> findAvailable() {
        List<Flat> listOfAll = this.flatsRepo.getRepo().findAll();
        List<Flat> listOfAvailable = new ArrayList<>();
        for (Flat x : listOfAll){
            if (x.getStatus() == FlatStatus.VACANT)
                listOfAvailable.add(x);
        }
        return listOfAvailable;
    }
}
