package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ApplyCouponRequest;
import com.example.entity.Coupons;
import com.example.service.CouponInterface;

@RestController
@RequestMapping("/coupons")
public class CouponController {
	    @Autowired
	    private CouponInterface couponService;
	    @PostMapping("/add")
	    public ResponseEntity<Coupons> createCoupon(@RequestBody Coupons coupon) {
	        Coupons createdCoupon = couponService.createCoupon(coupon);
	        return ResponseEntity.ok(createdCoupon);
	    }

	    // Get coupon by code
	    @GetMapping("/{couponcode}")
	    public ResponseEntity<Coupons> getCouponByCode(@PathVariable String couponcode) {
	        Optional<Coupons> couponOpt = couponService.getCouponByCode(couponcode);
	        return couponOpt.map(ResponseEntity::ok)
	                        .orElse(ResponseEntity.notFound().build());
	    }
	    @DeleteMapping("/{couponid}")
	    public ResponseEntity<Void> deleteCoupon(@PathVariable Integer couponid) {
	        couponService.deleteCoupon(couponid);
	        return ResponseEntity.noContent().build();
	    }    
	    @PostMapping("/apply")
	    public ResponseEntity<Double> applyCoupon(@RequestBody ApplyCouponRequest request) {
	        try {
	            double discountedAmount = couponService.applyCoupon(request.getCouponcode(), request.getTotalamount());
	            return ResponseEntity.ok(discountedAmount);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(null);
	        }    

}
	    @GetMapping("/all")
	    public ResponseEntity<List<Coupons>> getAllCoupons() {
	        List<Coupons> coupons = couponService.getAllCoupons();
	        return ResponseEntity.ok(coupons);
	    }
}
