package com.OnlineVotingSystem.OVS.service.event;

import com.OnlineVotingSystem.OVS.entity.Event;
import com.OnlineVotingSystem.OVS.model.event.EventRequest;
import com.OnlineVotingSystem.OVS.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EventServiceImplementation implements EventService{
    @Autowired
    private EventRepository eventRepository;
    @Override
    public ResponseEntity<String> addEvent(String path, MultipartFile file, EventRequest eventRequest) throws IOException, ParseException {
        String fileName = file.getOriginalFilename();
        String filePath = path + File.separator+fileName;
        String status = "";
        System.out.println(eventRequest);
        System.out.println(fileName);

        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));

        String finalPath = "C:/Users/user/Desktop/springboot/OVS/images/";
        String fileWithName= finalPath+ fileName;



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate startDate = LocalDate.parse(eventRequest.getStartDate(),formatter);
        LocalDate endDate = LocalDate.parse(eventRequest.getEndDate(),formatter);

        if(startDate.isAfter(LocalDate.now()) && endDate.isAfter(LocalDate.now())){

            status="Upcoming";
        } else if(startDate.isEqual(LocalDate.now())) {
            status="Ongoing";
        }else if(endDate.isBefore(LocalDate.now())){
            status="Expired";
        }
        System.out.println(status);
        Event event = Event.builder()
                .eventName(eventRequest.getEventName())
                .startDate(startDate)
                .endDate(endDate)
                .description(eventRequest.getDescription())
                .banner(fileWithName)
                .status(status)
                .build();
        eventRepository.save(event);
        return new ResponseEntity<>("Event has been added succesfully", HttpStatus.OK);
    }
}
