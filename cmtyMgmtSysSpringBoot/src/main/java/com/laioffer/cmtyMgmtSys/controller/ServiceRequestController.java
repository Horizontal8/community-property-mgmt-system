package com.laioffer.cmtyMgmtSys.controller;
import com.laioffer.cmtyMgmtSys.dto.ServiceRequestPostDto;
import com.laioffer.cmtyMgmtSys.entity.ServiceRequest;
import com.laioffer.cmtyMgmtSys.entity.ServiceRequestStatus;
import com.laioffer.cmtyMgmtSys.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceRequestController {
    @Autowired
    private ServiceRequestService serviceRequestService;

    /*
    Api Listing:
    1. addServiceRequest(ServiceRequest) -- SR body
            Return Value: ServiceRequest;
            Post on /serviceRequest/add

    2. getAllRequestFromCurrentUser: (@PathVariable Long id) -- resident ID
            Return Value: List<ServiceRequest> --all services under this resident
            Get on /serviceRequest/{residentID}

    3. getAllRequestFromCurrentUserByStatus: (@PathVariable Long id, @PathVarible String status)
            Return Value: List<ServiceRequest> -- all services under this resident and has this status
            Get on /serviceRequest/{residentId}/{status}
     */

    //add new service Request
    @PostMapping("/serviceRequest/add")
    public ServiceRequest addServiceRequest(@RequestBody ServiceRequestPostDto serviceRequestPostDto){
        System.out.println(serviceRequestPostDto.toString());
        return serviceRequestService.add(serviceRequestPostDto);
    }

    /*
    //get all serviceRequests by Resident Id
    @GetMapping("/serviceRequest/{residentId}")
    public List<ServiceRequest> getAllRequestFromCurrentUser(@PathVariable("residentId") Long residentId){
        return serviceRequestService.getAllRequestFromCurrentUser(residentId);
    }
     */

    //get all serviceRequest by Status
    @GetMapping("/serviceRequest/{status}")
    public List<ServiceRequest> getAllRequestFromCurrentUserByStatus (@PathVariable("status") String status){
        return serviceRequestService.getAllSRByCurUserWithStatus(ServiceRequestStatus.valueOf(status));
    }

}
