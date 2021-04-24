package com.laioffer.cmtyMgmtSys.controller;

import com.laioffer.cmtyMgmtSys.entity.CommonRoom;
import com.laioffer.cmtyMgmtSys.service.CommonRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommonRoomController {
    @Autowired
    CommonRoomService commonRoomService;

    @GetMapping("/commonroom/{id}")
    public CommonRoom getCommonRoomById(@PathVariable Long id){
        return commonRoomService.getCommonRoomById(id);
    }
}
