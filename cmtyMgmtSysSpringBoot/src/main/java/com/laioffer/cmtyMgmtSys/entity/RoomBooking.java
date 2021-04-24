package com.laioffer.cmtyMgmtSys.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "roombooking")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class RoomBooking extends Auditable<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private CommonRoom commonRoom;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident booker;
}
