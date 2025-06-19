package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Orderitems;

@Repository
public interface OrderItemRepository extends JpaRepository<Orderitems,Integer> {

}
