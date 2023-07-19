package com.OnlineVotingSystem.OVS.service.admin;

import com.OnlineVotingSystem.OVS.entity.Admin;
import com.OnlineVotingSystem.OVS.model.admin.AdminRequest;
import com.OnlineVotingSystem.OVS.model.admin.AdminResponse;
import com.OnlineVotingSystem.OVS.repository.AdminRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{


    @Autowired
    private AdminRepository adminRepository;

    private AdminResponse adminResponse;
    @Override
    public String registerAdmin(AdminRequest adminRequest) {

        if(adminRequest.getId() == null || adminRequest.getId().isEmpty()){
            adminRequest.setId(UUID.randomUUID().toString());;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode(adminRequest.getPassword());

        Admin admin = Admin.builder()
                .id(adminRequest.getId())
                .fullname(adminRequest.getFullname())
                .username(adminRequest.getUsername())
                .email(adminRequest.getEmail())
                .contact(adminRequest.getContact())
                .password(result)
                .photo(adminRequest.getPhoto())
                .build();

        adminRepository.save(admin);


        return "registered succesfully";
    }

    @Override
    public List<AdminResponse> getAdmin() {
       List<Admin> admin =adminRepository.findAll();
       List<AdminResponse> adminResponseList =admin.stream()
               .map(adminList ->{
                   AdminResponse response = new AdminResponse();
                   BeanUtils.copyProperties(adminList,response);
                   return response;
               }).collect(Collectors.toList());
        return adminResponseList;
    }

    @Override
    public ResponseEntity<String> updateAdmin(AdminRequest adminRequest) {
        Admin admin = adminRepository.findById(adminRequest.getId()).get();
        admin.setFullname(adminRequest.getFullname());
        admin.setUsername(adminRequest.getUsername());
        admin.setEmail(adminRequest.getEmail());
        admin.setContact(adminRequest.getContact());
        adminRepository.save(admin);

        return ResponseEntity.ok("User Updated Succesfully");
    }

    @Override
    public ResponseEntity<String> updatePassword(AdminRequest adminRequest) {

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
//        String result = encoder.encode(adminRequest.getPassword());
        System.out.println(adminRequest);
        System.out.println("Your current password is: " + adminRequest.getPassword());
        System.out.println("Your new password is"+adminRequest.getNewPassword());
        Admin admin = adminRepository.findById(adminRequest.getId()).get();
        System.out.println(admin);
        if(!admin.getPassword().equals(adminRequest.getPassword())){
            System.out.println("Your recenet password donot match");
            return new ResponseEntity<>("Your recent password donot match", HttpStatus.UNAUTHORIZED);

        }
        else {
            admin.setPassword(adminRequest.getNewPassword());
            adminRepository.save(admin);
            System.out.println(admin);
            System.out.println("Password changed successfully");
            return new ResponseEntity<>("Password changed succesfully", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> uploadPhoto(String path, MultipartFile file, AdminRequest adminRequest) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = path + File.separator+fileName;
        String status = "";

        System.out.println(fileName);

        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));

        String finalPath = "C:/Users/user/Desktop/springboot/OVS/images/";
        String fileWithName= finalPath+ fileName;

        Admin admin = adminRepository.findById(adminRequest.getId()).get();
        admin.setPhoto(fileWithName);
        adminRepository.save(admin);

        return new ResponseEntity<>("Photo changed successfully",HttpStatus.OK);
    }
}
