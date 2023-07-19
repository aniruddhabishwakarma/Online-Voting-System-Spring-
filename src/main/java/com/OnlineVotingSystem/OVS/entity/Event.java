package com.OnlineVotingSystem.OVS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
@Builder
@Entity
public class Event {
   @Id
    private String id;
    @Column(name="event_name")
    private String eventName;
    @Column(name="start_date")
    private LocalDate startDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @Column(name="description")
    private String description;
    @Column(name="banner")
    private String banner;
    @Column(name="status")
    private String status;
}
