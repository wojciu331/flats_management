package com.ttpsc.flats_management.model.repositories;

import com.ttpsc.flats_management.model.entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatsRepo extends JpaRepository<Flat, Long> {
}
