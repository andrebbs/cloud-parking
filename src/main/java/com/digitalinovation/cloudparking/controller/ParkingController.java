package com.digitalinovation.cloudparking.controller;

import com.digitalinovation.cloudparking.controller.dto.ParkingCreateDTO;
import com.digitalinovation.cloudparking.controller.dto.ParkingDTO;
import com.digitalinovation.cloudparking.controller.mapper.ParkingMapper;
import com.digitalinovation.cloudparking.model.Parking;
import com.digitalinovation.cloudparking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parking = (Parking) parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }
    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
            var parkingCreate = parkingMapper.toParkingCreate(dto);
            var parking = parkingService.create(parkingCreate);
            var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}