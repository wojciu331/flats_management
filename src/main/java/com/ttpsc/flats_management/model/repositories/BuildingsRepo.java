package com.ttpsc.flats_management.model.repositories;

import com.ttpsc.flats_management.model.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingsRepo extends JpaRepository <Building, Long> {
}
