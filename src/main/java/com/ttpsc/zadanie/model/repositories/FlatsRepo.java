package com.ttpsc.zadanie.model.repositories;

import com.ttpsc.zadanie.model.entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatsRepo extends JpaRepository<Flat, Long> {
}
