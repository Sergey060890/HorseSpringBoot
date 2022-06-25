package com.example.demo.service;

import com.example.demo.models.Horse;

import java.util.Optional;

public interface HorseService {
    Horse createHorse(String type, Integer age, Integer price);

    Iterable<Horse> findAll();

    void updateHorse(Integer id, String type, Integer age, Integer price);

    void deleteHorse(Integer id);

    Optional<Horse> findHorseById(Integer id);
}
