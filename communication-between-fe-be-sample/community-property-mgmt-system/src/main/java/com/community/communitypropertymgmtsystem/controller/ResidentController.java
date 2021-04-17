package com.community.communitypropertymgmtsystem.controller;

import com.community.communitypropertymgmtsystem.entity.Resident;
import com.community.communitypropertymgmtsystem.repository.ResidentRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class ResidentController {

    @Autowired
    private ResidentRepository residentRepository;

    @GetMapping("residents")
    public List<Resident> getResidents() {
        return this.residentRepository.findAll();
    }

    // create resident rest api
    @PostMapping("/residents")
    public Resident createResident(@RequestBody Resident resident) {
        return this.residentRepository.save(resident);
    }

    // get resident by id rest api
    @GetMapping("/residents/{id}")
    public ResponseEntity<Resident> getResidentById(@PathVariable Long id) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not exist with id: " + id));
        return ResponseEntity.ok(resident);
    }

    // update resident rest api
    @PutMapping("/residents/{id}")
    public ResponseEntity<Resident> updateResident(@PathVariable Long id, @RequestBody Resident residentDetails) {
        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resident not exist with id: " + id));
        resident.setFirstName(residentDetails.getFirstName());
        resident.setLastName(residentDetails.getLastName());
        resident.setPhone(residentDetails.getPhone());
        resident.setPreferredName(residentDetails.getPreferredName());
        Resident updatedResident = residentRepository.save(resident);
        return ResponseEntity.ok(updatedResident);
    }
}
