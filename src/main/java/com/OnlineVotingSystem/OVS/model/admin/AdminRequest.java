package com.OnlineVotingSystem.OVS.model.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {
    private String id;
    private String fullname;
    private String username;
    private String email;
    private String contact;
    private String password;
    private String newPassword;

    public String getFullname() {
        return fullname;
    }

    private String photo;
}
