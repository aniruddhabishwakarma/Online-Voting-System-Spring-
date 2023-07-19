package com.OnlineVotingSystem.OVS.model.user;

import com.OnlineVotingSystem.OVS.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String contact;
    private String userName;
    private String email;
    private String password;

}
