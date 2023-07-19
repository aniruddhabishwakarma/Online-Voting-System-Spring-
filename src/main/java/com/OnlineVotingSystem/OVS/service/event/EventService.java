package com.OnlineVotingSystem.OVS.service.event;

import com.OnlineVotingSystem.OVS.model.event.EventRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@Service
public interface EventService {
    ResponseEntity<String> addEvent(String path, MultipartFile file, EventRequest eventRequest) throws IOException, ParseException;
}
