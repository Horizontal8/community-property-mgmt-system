package com.laioffer.cmtyMgmtSys.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "apartment")
@Getter
@Setter
public class Apartment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String aptNum;
    private boolean avail;
}
