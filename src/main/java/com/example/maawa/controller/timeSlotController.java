package com.example.maawa.controller;

import com.example.maawa.dto.apiResponse;
import com.example.maawa.model.MyUser;
import com.example.maawa.model.timeSlot;
import com.example.maawa.service.timeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/timeSlot")
@RequiredArgsConstructor
public class timeSlotController {
    private final  timeSlotService timeSlotService;


    @GetMapping("/get")
    public ResponseEntity<List> getTimeSlot(){
        List<timeSlot> timeSlots=timeSlotService.getTimeSlot();
        return ResponseEntity.status(200).body(timeSlots);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addTimeSlot(@RequestBody @Valid timeSlot timeSlot, @AuthenticationPrincipal MyUser user){
        timeSlotService.addTimeSlot(timeSlot,user);
        return ResponseEntity.status(200).body(new apiResponse("Time slot Added Successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> deleteTimeSlot(@PathVariable Integer id,@AuthenticationPrincipal MyUser user){
        timeSlotService.deleteTimeSlot(id,user);
        return ResponseEntity.status(200).body(new apiResponse("Time Slot Deleted Successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<apiResponse> updateTimeSlot(@PathVariable Integer id ,@RequestBody @Valid timeSlot timeSlot ,@AuthenticationPrincipal MyUser user){
        timeSlotService.updateTimeSlot(id,timeSlot,user);
        return ResponseEntity.status(200).body(new apiResponse("Time Slot Updated Successfully!"));
    }

    @GetMapping("/byService")
    public ResponseEntity<List> getTimeSlotByService(@RequestBody Integer serviceId){
        List<timeSlot> timeSlots=timeSlotService.getTimeSlotByService(serviceId);
        return ResponseEntity.status(200).body(timeSlots);
    }

}
