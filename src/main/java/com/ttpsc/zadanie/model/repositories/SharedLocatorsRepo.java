package com.ttpsc.zadanie.model.repositories;

import org.springframework.stereotype.Component;

@Component
public class SharedLocatorsRepo {
    private static LocatorsRepo Repo;

    public static LocatorsRepo getRepo() {
        return Repo;
    }
}
