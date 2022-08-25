package com.example.maawa.controller;

import com.example.maawa.dto.EditAppointmentForm;
import com.example.maawa.dto.apiResponse;
import com.example.maawa.model.Appointment;
import com.example.maawa.model.MyUser;
import com.example.maawa.model.myOrder;
import com.example.maawa.service.appointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointment")
@RequiredArgsConstructor
public class appointmentController {

    private final appointmentService appointmentService;

    @GetMapping("/get")
    public ResponseEntity<List> getAppointments(){
        List<Appointment> appointments=appointmentService.getAppointments();
        return ResponseEntity.status(200).body(appointments);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addAppointment(@RequestBody @Valid Appointment appointment, @AuthenticationPrincipal MyUser user){
        appointmentService.addAppointment(appointment,user);
        return ResponseEntity.status(200).body(new apiResponse("Appointment Added Successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> deleteAppointment(@PathVariable Integer id,@AuthenticationPrincipal MyUser user){
        appointmentService.deleteAppointment(id,user);
        return ResponseEntity.status(200).body(new apiResponse("Appointment Deleted Successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<apiResponse> updateAppointment(@PathVariable Integer id , @RequestBody @Valid EditAppointmentForm appointment , @AuthenticationPrincipal MyUser user){
        appointmentService.updateAppointment(id,appointment,user);
        return ResponseEntity.status(200).body(new apiResponse("Appointment Updated Successfully!"));
    }

    @PutMapping("/changeStatus/{status}")
    public ResponseEntity<apiResponse> changeStatus(@PathVariable String status ,@RequestBody Integer appointmentId , @AuthenticationPrincipal MyUser user){
        appointmentService.changeStatus(status,appointmentId,user);
        return ResponseEntity.status(200).body(new apiResponse("Appointment status updated Successfully!"));
    }

    @GetMapping("/myAppointment")
    public ResponseEntity<List> getMyAppointment(@AuthenticationPrincipal MyUser user){
        List<Appointment> appointments=appointmentService.getMyAppointment(user);
        return ResponseEntity.status(200).body(appointments);
    }
}
