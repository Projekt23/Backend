package com.project23.app.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"statistics\"", schema = "public")
public class Statistic {
    @Id
    @Basic(optional = false)
    @Column(name = "timestamp", insertable = false, updatable = false)
    private ZonedDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    private BusinessObject businessObject;

    // 1 anlegen, 2 ändern, 3 anzeigen
    @Column(name="action")
    private int action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}