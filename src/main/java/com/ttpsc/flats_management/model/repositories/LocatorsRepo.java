package com.ttpsc.flats_management.model.repositories;

import com.ttpsc.flats_management.model.entities.Locator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatorsRepo extends JpaRepository <Locator, Long> {
}
