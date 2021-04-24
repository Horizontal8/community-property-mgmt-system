package com.laioffer.cmtyMgmtSys.service;

import com.laioffer.cmtyMgmtSys.dao.ServiceRequestRepository;
import com.laioffer.cmtyMgmtSys.dto.ServiceRequestPostDto;
import com.laioffer.cmtyMgmtSys.entity.ServiceRequest;
import com.laioffer.cmtyMgmtSys.entity.ServiceRequestStatus;
import com.laioffer.cmtyMgmtSys.entity.ServiceRequestType;
import com.laioffer.cmtyMgmtSys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ServiceRequestService {
    @Autowired
    ServiceRequestRepository serviceRequestRepository;

    public ServiceRequest add(@RequestBody ServiceRequestPostDto serviceRequestPostDto) {
        ServiceRequest temp = new ServiceRequest();
        temp.setDisabled(true);
        temp.setDescription(serviceRequestPostDto.getDescription());
        temp.setDesiredServiceTime(serviceRequestPostDto.getDesiredServiceTime());
        temp.setStatus(ServiceRequestStatus.valueOf(serviceRequestPostDto.getStatus()));
        if (serviceRequestPostDto.getType() != null) {
            temp.setType(ServiceRequestType.valueOf(serviceRequestPostDto.getType()));
        }
        return serviceRequestRepository.save(temp);
    }

    public List<ServiceRequest> getAllSRByCurUserWithStatus(@RequestBody ServiceRequestStatus status) {
        Object curPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long curUserId = 1L;
        if (curPrincipal instanceof User) {
            curUserId = ((User) curPrincipal).getId();
        }
        return serviceRequestRepository.findAllByCreatedByAndStatus(curUserId, status);
    }
}
