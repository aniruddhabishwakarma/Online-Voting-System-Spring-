package com.OnlineVotingSystem.OVS.service.user;

import com.OnlineVotingSystem.OVS.entity.User;
import com.OnlineVotingSystem.OVS.model.Role;
import com.OnlineVotingSystem.OVS.model.user.UserRequest;
import com.OnlineVotingSystem.OVS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository= userRepository;
    }
    public boolean isUsernameExists(String userName){
        return userRepository.existsByuserName(userName);
    }


    @Override
    public ResponseEntity<String> registerUser(UserRequest userRequest) {

        if(isUsernameExists(userRequest.getUserName())== true){
            return new ResponseEntity<>("User already exists",HttpStatus.NOT_ACCEPTABLE);
        }else{

            if(userRequest.getId() == null || userRequest.getId().isEmpty()){
                userRequest.setId(UUID.randomUUID().toString());
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            String result = encoder.encode(userRequest.getPassword());


            User user = User.builder()
                    .id(userRequest.getId())
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .contact(userRequest.getContact())
                    .email(userRequest.getEmail())
                    .userName(userRequest.getUserName())
                    .password(result)
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            return new ResponseEntity<>("User registration succesfull", HttpStatus.OK);
        }

    }
}
