package com.example.maawa.service;

import com.example.maawa.dto.GetItemForm;
import com.example.maawa.exception.apiException;
import com.example.maawa.model.*;
import com.example.maawa.repository.itemRepository;
import com.example.maawa.repository.myUserRepository;
import com.example.maawa.repository.orderRepository;
import com.example.maawa.repository.productRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class orderService {
    private final orderRepository orderRepository;
    private final myUserRepository myUserRepository;
    private final productRepository productRepository;
    private final itemService itemService;
    private final itemRepository itemRepository;


    public List<myOrder> getOrders() {
        return orderRepository.findAll();
    }


    public void addOrder(myOrder order, MyUser user) {
        MyUser store=myUserRepository.findMyUserById(order.getStoreId());
         if (store==null) {
           throw new apiException("Wrong storeId") ;
        }

        order.setUserId(user.getId());

        LocalDateTime time = LocalDateTime.now();
        order.setDateReceived(time);


        Double totalPrice = 0.0;
        List<Item> items=itemService.getMyItems(new GetItemForm(user.getId(),store.getId(),null));
        if (items.isEmpty()){
            throw new apiException("Your items cart is empty!");
        }

        for (int i = 0; i < items.size(); i++) {
            Product product=productRepository.findProductById(items.get(i).getProductId());
            totalPrice+=product.getPrice()*items.get(i).getQuantity();
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        for (int i = 0; i < items.size() ; i++) {
            Item item=itemRepository.findItemById(items.get(i).getId());
            item.setOrderId(order.getId());
            itemRepository.save(item);
        }
    }

    public void deleteOrder(Integer id,MyUser user) {
        myOrder order=orderRepository.findOrderById(id);
        if(order==null){
            throw new apiException("Wrong order ID!");
        }
        if (user.getRole().equals("customer")) {
            if (order.getUserId() != user.getId()) {
                throw new apiException("Sorry , You do not have the authority to cancel the order");
            }
        }else if (user.getRole().equals("store")) {
            if (order.getStoreId() != user.getId()) {
                throw new apiException("Sorry , You do not have the authority to cancel the order");
            }
        }

            if (order.getStatus().equals("inProgress")){
                throw new apiException("Sorry you can not cancel the order, because its in progress!");
            } else if (order.getStatus().equals("complete")) {
                throw new apiException("Sorry you can not cancel the order, because its completed!");
            }
            orderRepository.delete(order);

    }

    public void updateOrder(Integer id, myOrder order, MyUser user) {
        myOrder order1=orderRepository.findOrderById(id);
        if(order1==null){
            throw new apiException("Wrong order ID!");
        }
        if (order1.getUserId()!=user.getId()){
            throw new apiException("Sorry, You do not have the authority to update the order");
        }
        if (order1.getStatus().equals("inProgress")){
            throw new apiException("Sorry you can not update the order, because its in progress!");
        } else if (order1.getStatus().equals("complete")) {
            throw new apiException("Sorry you can not update the order, because its completed!");
        }
        order1.setIsPrepaid(order.getIsPrepaid());
        orderRepository.save(order1);
    }

    public void changeOrderState(String status,Integer orderId, MyUser user){
        myOrder order=orderRepository.findOrderById(orderId);
        if (order==null){
            throw new apiException("Wrong order Id");
        }
      if (order.getStoreId()!=user.getId()){
          throw new apiException("Sorry, You do not have the authority to change the status");
      }
      if (!status.equals("new")&&!status.equals("inProgress")&&!status.equals("complete")){
          throw new apiException("Status must be in new or inProgress or complete only");
      }
      order.setStatus(status);
      orderRepository.save(order);
    }

    public List<myOrder> getOrdersByStoreId(MyUser user){
        return orderRepository.findAllByStoreId(user.getId());
    }

    public List<myOrder> getMyOrders(MyUser user){
        return orderRepository.findAllByUserId(user.getId());
    }







}
