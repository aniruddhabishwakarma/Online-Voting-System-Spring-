package com.OnlineVotingSystem.OVS.model.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {
    private String id;
    private String fullname;
    private String username;
    private String email;
    private String contact;
    private String password;
    private String photo;
    private String message;
    public AdminResponse(String message){
        this.message = message;
    }
}
