package com.laioffer.cmtyMgmtSys.controller;

import com.laioffer.cmtyMgmtSys.dto.UpdateResponse;
import com.laioffer.cmtyMgmtSys.dao.ResidentRepository;
import com.laioffer.cmtyMgmtSys.entity.Resident;
import com.laioffer.cmtyMgmtSys.entity.RoomBooking;
import com.laioffer.cmtyMgmtSys.entity.User;
import com.laioffer.cmtyMgmtSys.service.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class RoomBookingController {
    @Autowired
    RoomBookingService roomBookingService;

    @Autowired
    private ResidentRepository residentRepository;

    @GetMapping("/events")
    public List<RoomBooking> getEvents() {
        return this.roomBookingService.getAllEvents();
    }

    // create event rest api
    @PostMapping("/events")
    public RoomBooking createRoomBooking(@RequestBody RoomBooking event) {
        return this.roomBookingService.createRoomBooking(event);
    }

    // update event rest api
    @PutMapping("/events/{id}")
    public ResponseEntity<String> updateRoomBooking(@PathVariable Long id, @RequestBody RoomBooking eventDetails) {
        //return this.roomBookingService.updateRoomBookingById(id, eventDetails);
        Resident resident = eventDetails.getBooker();
        resident = residentRepository.findById(resident.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Resident not exist with id: " + id));
        eventDetails.setBooker(resident);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) (authentication.getPrincipal());
        Map<String, UpdateResponse> res = this.roomBookingService.updateRoomBookingById(id, eventDetails, ((User) userDetails).getId());
        if (!Boolean.TRUE.equals(res.get("updated").getSuccess())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res.get("updated").getReason());
        }
        return ResponseEntity.ok().build();
    }

    // delete event rest api
    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteRoomBooking(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) (authentication.getPrincipal());
        Map<String, Boolean> res = this.roomBookingService.deleteRoomBooking(id, ((User) userDetails).getId());
        if (!Boolean.TRUE.equals(res.get("deleted"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only delete your own bookings.");
        }
        return ResponseEntity.ok().build();
    }
}
