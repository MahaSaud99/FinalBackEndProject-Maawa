package com.example.maawa.controller;

import com.example.maawa.dto.GeocodeResult;
import com.example.maawa.dto.apiResponse;
import com.example.maawa.model.MyUser;
import com.example.maawa.service.myUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/User")
@RequiredArgsConstructor
public class myUserController {

    private final myUserService userService;

    @GetMapping ("/get")
    public ResponseEntity<List> getUsers(){
        List<MyUser> users=userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addUser(@RequestBody @Valid MyUser user){
        userService.addUser(user);
        return ResponseEntity.status(200).body(new apiResponse("User Added Successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new apiResponse("User Deleted Successfully!"));
    }

    @PutMapping("/update")
    public ResponseEntity<apiResponse> updateUser(@RequestBody @Valid MyUser user, @AuthenticationPrincipal MyUser auth){
        userService.updateUser(user,auth);
        return ResponseEntity.status(200).body(new apiResponse("User Updated Successfully!"));
    }

    @GetMapping(path = "/geocode/{address}")
    public ResponseEntity<GeocodeResult> getGeocode(@PathVariable String address) throws IOException {
        GeocodeResult geocode= userService.getGeocode(address);
        return ResponseEntity.status(200).body(geocode);
    }

    @PutMapping("/makeApprovment/{isApproved}")
    public ResponseEntity<apiResponse> makeApprovment(@PathVariable Boolean isApproved,@RequestBody Integer id){
        userService.makeApprovment(isApproved,id);
        return ResponseEntity.status(200).body(new apiResponse("User Approved Successfully!"));
    }

//    @GetMapping ("/getUserInfo")
//    public ResponseEntity<MyUser> getUser(@AuthenticationPrincipal MyUser user){
//        MyUser myUser=userService.getUserInfo(user);
//        return ResponseEntity.status(200).body(myUser);
//    }

}
