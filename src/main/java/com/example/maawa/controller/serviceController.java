package com.example.maawa.controller;

import com.example.maawa.dto.apiResponse;
import com.example.maawa.model.MyUser;
import com.example.maawa.model.Product;
import com.example.maawa.model.Service;
import com.example.maawa.service.serviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
@RequiredArgsConstructor
public class serviceController {

    private final serviceService serviceService;


    @GetMapping("/get")
    public ResponseEntity<List> getServices(){
        List<Service> services=serviceService.getServices();
        return ResponseEntity.status(200).body(services);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addService(@RequestBody @Valid Service service,@AuthenticationPrincipal MyUser user){
        serviceService.addService(service,user);
        return ResponseEntity.status(200).body(new apiResponse("Service Added Successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> deleteService(@PathVariable Integer id,@AuthenticationPrincipal MyUser user){
        serviceService.deleteService(id,user);
        return ResponseEntity.status(200).body(new apiResponse("Service Deleted Successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<apiResponse> updateService(@PathVariable Integer id ,@RequestBody @Valid Service service,@AuthenticationPrincipal MyUser user){
        serviceService.updateService(id,service,user);
        return ResponseEntity.status(200).body(new apiResponse("Service Updated Successfully!"));
    }

    @GetMapping("/myServices")
    public ResponseEntity<List> getMyServices(@AuthenticationPrincipal MyUser user){
        List<Service> services= serviceService.getMyProducts(user);
        return ResponseEntity.status(200).body(services);
    }

    @GetMapping("/byClinic")
    public ResponseEntity<List> getServicesByClinic(@RequestBody Integer clinicId){
        List<Service> services= serviceService.getServicesByClinic(clinicId);
        return ResponseEntity.status(200).body(services);
    }
}
