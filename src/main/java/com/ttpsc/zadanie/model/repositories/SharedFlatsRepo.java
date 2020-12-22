package com.ttpsc.zadanie.model.repositories;

import org.springframework.stereotype.Component;

@Component
public class SharedFlatsRepo {
    private static FlatsRepo Repo;

    public static FlatsRepo getRepo() {
        return Repo;
    }
}
