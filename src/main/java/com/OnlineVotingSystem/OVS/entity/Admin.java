package com.OnlineVotingSystem.OVS.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
    @Id
    private String id;

    @Column(name="full_name")
    private String fullname;

    @Column(name="user_name")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="contact")
    private String contact;

    @Column(name="password")
    private String password;

    @Column(name="photo")
    private String photo;
}
