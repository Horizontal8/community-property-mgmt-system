package com.laioffer.cmtyMgmtSys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLock;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "resident")
@Getter
@Setter
public class Resident implements Serializable{
    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String preferredName;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = true)//unique foreign key make sure it is one-to-one
    @OptimisticLock(excluded = true)
    @JsonBackReference
    private User user;

    @ManyToOne()
    private Apartment apartment;
}
