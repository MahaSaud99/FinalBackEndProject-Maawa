package com.example.maawa.repository;

import com.example.maawa.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface itemRepository extends JpaRepository<Item,Integer> {
    Item findItemById(Integer id);
    List<Item> findItemsByUserIdAndStoreIdAndOrderId(Integer userId, Integer storeId,Integer orderId);
    List<Item> findAllByOrderId(Integer id);

   // List<Item> findItemsByUserIdAndStoreIdAndOrderId

}
