package com.OnlineVotingSystem.OVS.service.user;

import com.OnlineVotingSystem.OVS.model.user.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<String> registerUser(UserRequest userRequest);
}
