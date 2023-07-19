package com.OnlineVotingSystem.OVS.controller;

import com.OnlineVotingSystem.OVS.model.admin.AdminRequest;
import com.OnlineVotingSystem.OVS.model.admin.AdminResponse;
import com.OnlineVotingSystem.OVS.model.event.EventRequest;
import com.OnlineVotingSystem.OVS.service.admin.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController {
    @Value("${project.image}")
    private String path;

    @Autowired
    private AdminService adminService;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping
    public ResponseEntity<AdminResponse> registerAdmin(@RequestBody AdminRequest adminRequest){
        adminService.registerAdmin(adminRequest);
        return new ResponseEntity<>(new AdminResponse("Admin has been registered Succesfully"), HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAdminData(){
        List<AdminResponse> adminResponse = adminService.getAdmin();
        return new ResponseEntity<>(adminResponse,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateAdmin(@RequestBody AdminRequest adminRequest){
        adminService.updateAdmin(adminRequest);
        return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
    }
    @PutMapping("/password")
    public ResponseEntity<String> updatePassword(@RequestBody AdminRequest adminRequest){

        if(adminRequest.getPassword().equals(adminRequest.getNewPassword())){
            return new ResponseEntity<>("Both cannot be same",HttpStatus.UNAUTHORIZED);
        }

        return adminService.updatePassword(adminRequest);
    }
    @PutMapping("/image")
    public ResponseEntity<String> updatePhoto(@RequestParam("file") MultipartFile file,@RequestParam("admin") String admin) throws IOException {
        AdminRequest adminRequest = mapper.readValue(admin,AdminRequest.class);
        return adminService.uploadPhoto(path,file,adminRequest);
    }
}
