package com.laioffer.cmtyMgmtSys.service;

import com.laioffer.cmtyMgmtSys.dao.CommonRoomRepository;
import com.laioffer.cmtyMgmtSys.entity.CommonRoom;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonRoomService {
    @Autowired
    CommonRoomRepository commonRoomRepository;
    public CommonRoom getCommonRoomById(Long id) {
        return commonRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not exist with id: " + id));
    }
}
