package com.laioffer.cmtyMgmtSys.controller;
import com.laioffer.cmtyMgmtSys.dao.SchedulerRepository;
import com.laioffer.cmtyMgmtSys.entity.DisBoardPost;
import com.laioffer.cmtyMgmtSys.entity.RoomBooking;
import com.laioffer.cmtyMgmtSys.service.SchedulerService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class SchedulerController {
    @Autowired
    SchedulerService postService;

    @GetMapping("/allRoomBookings")
    public List<RoomBooking> getAllRoomBookings() {
        return postService.getAllBookings();
    }

    @GetMapping("/allRoomBookings/id")
    public Optional<RoomBooking> getRoomBookingsById(Long id) {
        return postService.getBookingById(id);
    }

    @GetMapping("/allWeekBookings")
    public List<RoomBooking> getAllWeekBookings(){
        return postService.getBookingByWeek();
    }

    @GetMapping("/allWeekByRoomBookings")
    public List<RoomBooking> getAllWeekBookings(Long room_id){
        return postService.getBookingByWeekByRoom(room_id);
    }

    @PostMapping("/postNewRoomBooking")
    public RoomBooking addNewRoomBooking(@RequestBody RoomBooking booking) {
        return postService.addNewRoomBooking(booking);
    }

    @DeleteMapping("/deleteAllRoomBooking")
    public String deleteRoomBooking() {
        return "Number of Entries Deleted: " + postService.deleteAll().size();
    }

    @DeleteMapping("/deleteAllRoomBooking/id")
    public String deleteRoomBooking(Long id) {
        return postService.deleteById(id);
    }

    @PutMapping ("/putRoomBooking/id")
    public String putRoomBooking(Long id, String startTime, String endTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start = format.parse(startTime);
        Date end = format.parse(endTime);
        return postService.putByTime(id, start, end);
    }



}


