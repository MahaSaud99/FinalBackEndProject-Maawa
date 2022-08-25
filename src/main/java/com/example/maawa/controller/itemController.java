package com.example.maawa.controller;

import com.example.maawa.dto.GetItemForm;
import com.example.maawa.dto.apiResponse;
import com.example.maawa.model.Item;
import com.example.maawa.model.MyUser;
import com.example.maawa.service.itemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class itemController {

    private final itemService itemService;


    @GetMapping("/get")
    public ResponseEntity<List> getItems(){
        List<Item> items=itemService.getItems();
        return ResponseEntity.status(200).body(items);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addItem(@RequestBody @Valid Item item , @AuthenticationPrincipal MyUser user){
        itemService.addItem(item,user);
        return ResponseEntity.status(200).body(new apiResponse("Item Added Successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> deleteItem(@PathVariable Integer id , @AuthenticationPrincipal MyUser user){
        itemService.deleteItem(id,user);
        return ResponseEntity.status(200).body(new apiResponse("Item Deleted Successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<apiResponse> updateItem(@PathVariable Integer id ,@RequestBody @Valid Item item , @AuthenticationPrincipal MyUser user){
        itemService.updateItem(id,item,user);
        return ResponseEntity.status(200).body(new apiResponse("Item Updated Successfully!"));
    }

    @GetMapping("/MyItems")
    public ResponseEntity<List> getMyItems(GetItemForm getItemForm){
        List<Item> items=itemService.getMyItems(getItemForm);
        return ResponseEntity.status(200).body(items);
    }

    @GetMapping("/getOrderItems")
    public ResponseEntity<List> getOrderItems(@RequestBody Integer orderId, @AuthenticationPrincipal MyUser user){
        List<Item> items=itemService.getOrderItems(orderId,user);
        return ResponseEntity.status(200).body(items);
    }
}
