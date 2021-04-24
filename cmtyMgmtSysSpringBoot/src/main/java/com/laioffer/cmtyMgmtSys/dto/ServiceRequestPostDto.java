package com.laioffer.cmtyMgmtSys.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ServiceRequestPostDto {
    String status;
    String type;
    private String description;
    private Date desiredServiceTime;
}
