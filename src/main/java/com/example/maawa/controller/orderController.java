package com.example.maawa.controller;

import com.example.maawa.dto.apiResponse;
import com.example.maawa.model.Item;
import com.example.maawa.model.MyUser;
import com.example.maawa.model.myOrder;
import com.example.maawa.service.orderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class orderController {

    private final orderService orderService;


    @GetMapping("/get")
    public ResponseEntity<List> getOrders(){
        List<myOrder> orders=orderService.getOrders();
        return ResponseEntity.status(200).body(orders);
    }

    @PostMapping("/add")
    public ResponseEntity<apiResponse> addOrder(@RequestBody @Valid myOrder order , @AuthenticationPrincipal MyUser user){
        orderService.addOrder(order,user);
        return ResponseEntity.status(200).body(new apiResponse("Order Added Successfully!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<apiResponse> deleteOrder(@PathVariable Integer id, @AuthenticationPrincipal MyUser user){
        orderService.deleteOrder(id,user);
        return ResponseEntity.status(200).body(new apiResponse("Order canceled Successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<apiResponse> updateOrder(@PathVariable Integer id ,@RequestBody @Valid myOrder order, @AuthenticationPrincipal MyUser user){
        orderService.updateOrder(id,order,user);
        return ResponseEntity.status(200).body(new apiResponse("Order Updated Successfully!"));
    }

    @PutMapping("/status/{status}")
    public ResponseEntity<apiResponse> changeOrderStatus(@PathVariable String status, @RequestBody Integer orderId , @AuthenticationPrincipal MyUser user){
        orderService.changeOrderState(status,orderId,user);
        return ResponseEntity.status(200).body(new apiResponse("Status Updated Successfully!"));
    }

    @GetMapping("/byStoreId")
    public ResponseEntity<List> getOrdersByStoreId(@AuthenticationPrincipal MyUser user){
        List<myOrder> orders=orderService.getOrdersByStoreId(user);
        return ResponseEntity.status(200).body(orders);
    }

    @GetMapping("/myOrders")
    public ResponseEntity<List> getMyOrders(@AuthenticationPrincipal MyUser user){
        List<myOrder> orders=orderService.getMyOrders(user);
        return ResponseEntity.status(200).body(orders);
    }


}

