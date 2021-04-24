package com.laioffer.cmtyMgmtSys.dao;

import com.laioffer.cmtyMgmtSys.entity.CommonRoom;
import com.laioffer.cmtyMgmtSys.entity.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository <RoomBooking, Long> {

    List<RoomBooking> findAllByCommonRoomAndStartTimeLessThanAndEndTimeGreaterThan(CommonRoom room, Date startTime, Date endTime);

    List<RoomBooking> findAllByCommonRoomAndStartTimeGreaterThanAndEndTimeLessThan(CommonRoom room, Date startTime, Date endTime);

    List<RoomBooking> findAllByCommonRoomAndStartTimeEqualsOrCommonRoomAndEndTimeEquals(CommonRoom room1, Date startTime, CommonRoom room2, Date endTime);
}
