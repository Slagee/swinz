package com.swinz.swinz.dao;

import com.swinz.swinz.model.House;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepository extends CrudRepository<House, Integer> {
}