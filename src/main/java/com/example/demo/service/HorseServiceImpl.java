package com.example.demo.service;

import com.example.demo.models.Horse;
import com.example.demo.repo.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class HorseServiceImpl implements HorseService {

    @Autowired
    private HorseRepository horseRepository;

    @Override
    public Horse createHorse(String type, Integer age, Integer price) {
        Horse horse = Horse.builder()
                .type(type)
                .age(age)
                .price(price)
                .build();
        horseRepository.save(horse);
        return horse;
    }

    @Override
    public Iterable<Horse> findAll() {
        return horseRepository.findAll();
    }

    @Override
    public void updateHorse(Integer id, String type, Integer age, Integer price) {
        Horse horse = horseRepository.findById(id).orElseThrow();
        horse.setType(type);
        horse.setAge(age);
        horse.setPrice(price);
        horseRepository.save(horse);
    }

    @Override
    public void deleteHorse(Integer id) {
        Horse horse = horseRepository.findById(id).orElseThrow();
        horseRepository.delete(horse);
    }

    @Override
    public Optional<Horse> findHorseById(Integer id) {
        Optional<Horse> horse = horseRepository.findById(id);
        return horse;
    }
}
