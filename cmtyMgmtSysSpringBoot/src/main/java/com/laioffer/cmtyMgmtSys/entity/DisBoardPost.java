package com.laioffer.cmtyMgmtSys.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "disboardpost")
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class DisBoardPost extends Auditable<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String content;
    private String title;
    private String tag;//reserved for the future
}