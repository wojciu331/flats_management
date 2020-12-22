package com.ttpsc.zadanie.model.repositories;

import com.ttpsc.zadanie.model.entities.Locator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatorsRepo extends JpaRepository <Locator, Long> {
}
