package com.OnlineVotingSystem.OVS.controller;

import com.OnlineVotingSystem.OVS.model.event.EventRequest;
import com.OnlineVotingSystem.OVS.service.event.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins="*")
public class EventController {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EventService eventService;

    @Value("${project.image}")
    private String path;

    @PostMapping
    public ResponseEntity<String> addEvent(@RequestParam("file") MultipartFile file,
                                           @RequestParam("event") String event) throws IOException, ParseException {

        EventRequest eventrequest = mapper.readValue(event,EventRequest.class);

        return eventService.addEvent(path,file,eventrequest);
    }
}
