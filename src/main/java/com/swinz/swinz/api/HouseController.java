package com.swinz.swinz.api;

import com.swinz.swinz.model.House;
import com.swinz.swinz.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @RequestMapping("/houses")
    public List<House> getAllHouses() {
        return houseService.getAllHouses();
    }

    @RequestMapping("/houses/{id}")
    public House getHouseByID(@PathVariable int id) {
        return houseService.getHouseByID(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/houses")
    public void addHouse(@RequestBody House house) {
        houseService.addHouse(house);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/houses/{id}")
    public void updateHouse(@RequestBody House house, @PathVariable int id) {
        houseService.updateHouse(house, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/houses/{id}")
    public void deleteHouseByID(@PathVariable int id) {
        houseService.deleteHouseByID(id);
    }


}
