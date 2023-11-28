package com.example.mib2_integrate.controller;

import com.example.mib2_integrate.payload.RequestDto;
import com.example.mib2_integrate.repository.RequestRepository;
import com.example.mib2_integrate.service.RequestMibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mib2")
public class Mib2ApiController {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestMibService requestMibService;

    @PostMapping("/integrate")
    public HttpEntity<?> getWithPinfl(@RequestBody RequestDto requestDto){
        try {
            HttpEntity<?> personWithPnfl = requestMibService.getPersonWithPnfl(requestDto);
            return personWithPnfl;
        }catch (Exception e){
            return ResponseEntity.status(404).body("Not Found");
        }
    }

}
