package com.OnlineVotingSystem.OVS.entity;

import com.OnlineVotingSystem.OVS.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="contact")
    private String contact;
    @Column(name="userName")
    private String userName;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
