package com.swinz.swinz.service;

import com.swinz.swinz.dao.HouseRepository;
import com.swinz.swinz.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public List<House> getAllHouses() {
        List<House> houses = new ArrayList<>();
        houseRepository.findAll().forEach(houses::add);
        return houses;
    }

    public House getHouseByID(int id) {
        return houseRepository.findById(id).orElseThrow();
    }

    public void addHouse(House house) {
        houseRepository.save(house);
    }

    public void updateHouse(House house, int id) {
        house.setID(id);
        houseRepository.save(house);
    }

    public void deleteHouseByID(int id) {
        houseRepository.deleteById(id);
    }
}
