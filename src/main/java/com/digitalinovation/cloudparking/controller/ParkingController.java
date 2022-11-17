package com.digitalinovation.cloudparking.controller;

import com.digitalinovation.cloudparking.controller.mapper.ParkingMapper;
import com.digitalinovation.cloudparking.model.Parking;
import com.digitalinovation.cloudparking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

        public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper){
            this.parkingService = parkingService;
            this.parkingMapper = parkingMapper;
        }
    @GetMapping
    public List<ParkingDTO> findAll(){
          //  return parkingService.findAll();
        List<Parking> parkingList = parkingService.findAll();

        //modo tradicional foreach com iterator
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return result;
    }
}