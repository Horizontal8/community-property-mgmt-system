package com.laioffer.cmtyMgmtSys.service;

import com.laioffer.cmtyMgmtSys.dao.SchedulerRepository;
import com.laioffer.cmtyMgmtSys.entity.CommonRoom;
import com.laioffer.cmtyMgmtSys.entity.RoomBooking;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
@Service
public class SchedulerService {

    @Autowired
    SchedulerRepository postRepo2;


    public List<RoomBooking> getAllBookings() {
        return postRepo2.findAll();
    }


    public Optional<RoomBooking> getBookingById(Long id) {
        System.out.println(postRepo2.findById(id) + " " + postRepo2.findById(id).getClass());
        return postRepo2.findById(id);
    }

    public List<RoomBooking> getBookingByWeek(){
        Calendar c=Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);

        Date past_sunday = (c.getTime());
        c.add(Calendar.DATE,7);
        Date next_sunday = (c.getTime());

        List<RoomBooking> all = postRepo2.findAll();
        List<RoomBooking> ret = null;
        for (RoomBooking booking : all) {
            if((past_sunday.compareTo(booking.getStartTime()) <= 0)
                    && (next_sunday.compareTo(booking.getEndTime()) > 0) ){
                ret.add(booking);
            }

        }
        return ret;


    }


    public List<RoomBooking> getBookingByWeekByRoom(Long room_id) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);

        Date past_sunday = (c.getTime());
        c.add(Calendar.DATE,7);
        Date next_sunday = (c.getTime());


        List<RoomBooking> all = postRepo2.findAll();
        List<RoomBooking> ret = null;
        for (RoomBooking booking : all) {
            if(booking.getCommonRoom().getId().equals(room_id)
                    && (past_sunday.compareTo(booking.getStartTime()) <= 0)
                    && (next_sunday.compareTo(booking.getEndTime()) > 0) ){
                ret.add(booking);
            }

        }
        return ret;

    }



    public RoomBooking addNewRoomBooking(RoomBooking booking) {
        return postRepo2.save(booking);
    }


    public List<RoomBooking> deleteAll() {
        List<RoomBooking> bookings = postRepo2.findAll();
        postRepo2.deleteAll();
        return bookings;
    }

    public String deleteById(Long id) {
        if (!postRepo2.existsById(id)) {
            return "No Entry Found";
        } else {
            postRepo2.deleteById(id);
            return "Delete Successfully";
        }
    }

    public String putByTime(Long id, Date start, Date end) {
        if (postRepo2.existsById(id)) {
            RoomBooking booking = postRepo2.findById(id).get();
            booking.setStartTime(start);
            booking.setEndTime(end);
            postRepo2.save(booking);

            return "Booking time of id: " + id + " reset successfully";
        } else {
            return "No record found";
        }
    }



}
