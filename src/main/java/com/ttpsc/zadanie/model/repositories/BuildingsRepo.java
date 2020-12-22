package com.ttpsc.zadanie.model.repositories;

import com.ttpsc.zadanie.model.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingsRepo extends JpaRepository <Building, Long> {
}
