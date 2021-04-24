package com.laioffer.cmtyMgmtSys.service;

import com.laioffer.cmtyMgmtSys.dto.UpdateResponse;
import com.laioffer.cmtyMgmtSys.dao.RoomBookingRepository;
import com.laioffer.cmtyMgmtSys.entity.CommonRoom;
import com.laioffer.cmtyMgmtSys.entity.RoomBooking;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class RoomBookingService {

    @Autowired
    RoomBookingRepository roomBookingRepository;

    public List<RoomBooking> getAllEvents(){
        return roomBookingRepository.findAll();
    }

    public RoomBooking createRoomBooking(RoomBooking roomBooking){
        return roomBookingRepository.save(roomBooking);
    }

    public Map<String, UpdateResponse> updateRoomBookingById(Long id, RoomBooking newRoomBooking, Long currentUserId){
        RoomBooking target = roomBookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not exist with id: " + id));
        Map<String, UpdateResponse> response = new HashMap<>();
        if (!hasAccess(newRoomBooking.getBooker().getUser().getId(), currentUserId)){
            response.put("updated", new UpdateResponse(Boolean.FALSE,"You can only update your own bookings."));
        } else if (hasOverlap(newRoomBooking)){
            response.put("updated", new UpdateResponse(Boolean.FALSE,"Room is not available for the given time period."));
        } else {
            target.setCommonRoom(newRoomBooking.getCommonRoom());
            target.setStartTime(newRoomBooking.getStartTime());
            target.setEndTime(newRoomBooking.getEndTime());
            roomBookingRepository.save(target);
            response.put("updated", new UpdateResponse(Boolean.TRUE,null));
        }
        return response;
    }

    public Map<String, Boolean> deleteRoomBooking(@PathVariable Long id, Long currentUserId) {
        RoomBooking event = roomBookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not exist with id: " + id));
        Map<String, Boolean> response = new HashMap<>();
        if (!hasAccess(event.getBooker().getUser().getId(), currentUserId)) {
            response.put("deleted", Boolean.FALSE);
        } else {
            roomBookingRepository.delete(event);
            response.put("deleted", Boolean.TRUE);
        }
        return response;
    }

    private boolean hasAccess(Long userId, Long currentUserId) {
        return Objects.nonNull(userId) && Objects.nonNull(currentUserId) && userId.equals(currentUserId);
    }

    private boolean hasOverlap(RoomBooking newRoomBooking) {
        Date startDate = newRoomBooking.getStartTime();
        Date endDate = newRoomBooking.getEndTime();
        CommonRoom room = newRoomBooking.getCommonRoom();
        List<RoomBooking> bookings = roomBookingRepository.findAllByCommonRoomAndStartTimeLessThanAndEndTimeGreaterThan(room, startDate, startDate);
        if (!bookings.isEmpty()) {
            return true;
        }
        bookings = roomBookingRepository.findAllByCommonRoomAndStartTimeLessThanAndEndTimeGreaterThan(room, endDate, endDate);
        if (!bookings.isEmpty()) {
            return true;
        }
        bookings = roomBookingRepository.findAllByCommonRoomAndStartTimeGreaterThanAndEndTimeLessThan(room, startDate, endDate);
        if (!bookings.isEmpty()) {
            return true;
        }
        bookings = roomBookingRepository.findAllByCommonRoomAndStartTimeEqualsOrCommonRoomAndEndTimeEquals(room, startDate, room, endDate);
        return !bookings.isEmpty();
    }
}
