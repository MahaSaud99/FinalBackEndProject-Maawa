package com.example.maawa.controller;

import com.example.maawa.dto.apiResponse;
import com.example.maawa.model.MyUser;
import com.example.maawa.model.Picture;
import com.example.maawa.model.myOrder;
import com.example.maawa.service.pictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/picture")
@RequiredArgsConstructor
public class pictureController {

    private final pictureService pictureService;

    @GetMapping("/get")
    public ResponseEntity<List> getPictures(){
        List<Picture> pictures=pictureService.getPicture();
        return ResponseEntity.status(200).body(pictures);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addPicture(@RequestBody @Valid Picture picture , @AuthenticationPrincipal MyUser user){
        pictureService.addPicture(picture,user);
        return ResponseEntity.status(200).body(new apiResponse("Picture Added Successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> deletePicture(@PathVariable Integer id , @AuthenticationPrincipal MyUser user){
        pictureService.deletePicture(id,user);
        return ResponseEntity.status(200).body(new apiResponse("Picture Deleted Successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<apiResponse> updatePicture(@PathVariable Integer id ,@RequestBody @Valid Picture picture , @AuthenticationPrincipal MyUser user){
        pictureService.updatePicture(id,picture,user);
        return ResponseEntity.status(200).body(new apiResponse("Picture Updated Successfully!"));
    }

    @GetMapping("/byProduct")
    public ResponseEntity<List> getPicturesByProduct(@RequestBody Integer productId){
       List<Picture> pictures=pictureService.getPicturesByProduct(productId);
        return ResponseEntity.status(200).body(pictures);
    }
}
