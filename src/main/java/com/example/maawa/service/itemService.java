package com.example.maawa.service;

import com.example.maawa.dto.GetItemForm;
import com.example.maawa.exception.apiException;
import com.example.maawa.model.*;
import com.example.maawa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class itemService {

    private final itemRepository itemRepository;
    private final productRepository productRepository ;
    private final myUserRepository myUserRepository;
    private final orderRepository orderRepository;



    public List<Item> getItems() {
        return itemRepository.findAll();
    }


    public void addItem(Item item , MyUser user) {
        MyUser store=myUserRepository.findMyUserById(item.getStoreId());
        Product product=productRepository.findProductById(item.getProductId());
        if (store==null){
            throw new apiException("Wrong storeId");
        } else if (product==null) {
            throw new apiException("Wrong productId") ;
        }else if(store.getId()!=product.getStoreId()){
            throw new apiException("Wrong productId") ;
        }
        item.setUserId(user.getId());
        itemRepository.save(item);
    }

    public void deleteItem(Integer id ,MyUser user) {
        Item item=itemRepository.findItemById(id);
        if(item==null){
            throw new apiException("Wrong item ID!");
        }
        if (item.getUserId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to delete this item");
        }
        if (item.getOrderId()!=null){
            throw new apiException("Sorry , You can not delete this item");
        }
        itemRepository.delete(item);
    }

    public void updateItem(Integer id, Item item,MyUser user) {
        Item item1=itemRepository.findItemById(id);

        if(item1==null){
            throw new apiException("Wrong item ID!");
        }
        if (item1.getUserId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to update this item");
        }
        item1.setQuantity(item.getQuantity());
        itemRepository.save(item1);
    }

    public List<Item> getMyItems(GetItemForm getItemForm) {
        return itemRepository.findItemsByUserIdAndStoreIdAndOrderId(getItemForm.getUserId(), getItemForm.getStoreId(),getItemForm.getOrderId());
    }

    public List<Item> getOrderItems(Integer orderId, MyUser user){
        myOrder order=orderRepository.findOrderById(orderId);
        if (order==null){
            throw new apiException("Wrong orderId");
        }
        if (user.getRole().equals("customer")){
            if (user.getId()!=order.getUserId()){
                throw new apiException("Sorry , You do not have the authority to view this items");
            }
        }else if (user.getRole().equals("store")){
            if (user.getId()!=order.getStoreId()){
                throw new apiException("Sorry , You do not have the authority to view this items");
            }
        }
        return itemRepository.findAllByOrderId(orderId);
    }

}
