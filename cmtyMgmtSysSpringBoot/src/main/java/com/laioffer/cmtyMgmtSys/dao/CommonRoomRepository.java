package com.laioffer.cmtyMgmtSys.dao;

import com.laioffer.cmtyMgmtSys.entity.CommonRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CommonRoomRepository extends JpaRepository<CommonRoom, Long> {
}
