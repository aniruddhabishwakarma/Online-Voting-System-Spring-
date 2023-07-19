package com.OnlineVotingSystem.OVS.service.admin;

import com.OnlineVotingSystem.OVS.model.admin.AdminRequest;
import com.OnlineVotingSystem.OVS.model.admin.AdminResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface AdminService {
    String registerAdmin(AdminRequest adminRequest);

    List<AdminResponse> getAdmin();


    ResponseEntity<String> updateAdmin(AdminRequest adminRequest);

    ResponseEntity<String> updatePassword(AdminRequest adminRequest);

    ResponseEntity<String> uploadPhoto(String path, MultipartFile file,AdminRequest adminRequest) throws IOException;
}
