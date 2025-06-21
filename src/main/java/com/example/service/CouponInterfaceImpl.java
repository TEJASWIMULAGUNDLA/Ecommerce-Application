package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Coupons;
import com.example.entity.Discounttype;
import com.example.repo.CouponsRepository;

@Service
public class CouponInterfaceImpl implements CouponInterface{
	 @Autowired
	    private CouponsRepository couponsRepository;

	    @Override
	    public Coupons createCoupon(Coupons coupon) {
	    	coupon.setCreatedtime(LocalDate.now());
	    	coupon.setExiparydate(LocalDate.now().plusDays(15));
	    	// Always set creation time

	        return couponsRepository.save(coupon);
	    }

	    @Override
	    public Optional<Coupons> getCouponByCode(String couponcode) {
	        return couponsRepository.findByCouponcode(couponcode);
	    }

	    @Override
	    public boolean isCouponValid(Coupons coupon, double totalAmount) {
	        return coupon.getExiparydate().isAfter(LocalDate.now()) &&
	               totalAmount >= coupon.getMinorderAmount();
	    }

	 

	    @Override
	    public void deleteCoupon(Integer couponid) {
	        couponsRepository.deleteById(couponid);
	    }
	    @Override
	    public double applyCoupon(String couponcode, double totalAmount) {
	        Optional<Coupons> couponOpt = couponsRepository.findByCouponcode(couponcode);

	        if (couponOpt.isEmpty()) {
	            throw new IllegalArgumentException("Coupon not found.");
	        }

	        Coupons coupon = couponOpt.get();
	        

	        if (!isCouponValid(coupon, totalAmount)) {
	            throw new IllegalArgumentException("Coupon is not valid for the given amount.");
	        }

	        double discountedAmount;

	        if (coupon.getDiscountType() == Discounttype.PERCENTAGE) {
	            discountedAmount = totalAmount - (totalAmount * coupon.getDiscountvalue() / 100);
	        } else if (coupon.getDiscountType() == Discounttype.FIXEDAMOUNT) {
	            discountedAmount = totalAmount - coupon.getDiscountvalue();
	        } else {
	            throw new IllegalArgumentException("Unknown discount type.");
	        }

	        return Math.max(discountedAmount, 0); // avoid negative total
	    }   
	    @Override
	    public List<Coupons> getAllCoupons() {
	        return couponsRepository.findAll();
	    }
	    
}
