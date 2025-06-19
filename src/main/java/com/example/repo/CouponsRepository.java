package com.example.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Coupons;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons,Integer> {
	Optional<Coupons> findByCouponcode(String couponcode);
}
