package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Coupons;

public interface CouponInterface {
	Coupons createCoupon(Coupons coupon);

    Optional<Coupons> getCouponByCode(String couponcode);

    boolean isCouponValid(Coupons coupon, double totalamount);

    void deleteCoupon(Integer couponid);
    double applyCoupon(String code, double totalAmount);
    List<Coupons> getAllCoupons(); // ← Add this method

}
