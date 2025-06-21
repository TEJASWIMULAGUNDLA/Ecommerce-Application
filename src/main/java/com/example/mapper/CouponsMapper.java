package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.dto.CouponDTO;
import com.example.entity.Coupons;

@Component
public class CouponsMapper {
	 public CouponDTO toDTO(Coupons entity) {
	        CouponDTO dto = new CouponDTO();
	        dto.setCouponid(entity.getCouponid());
	        dto.setCouponcode(entity.getCouponcode());
	        dto.setDescription(entity.getDecsription());
	        dto.setDiscountValue(entity.getDiscountvalue());
	        dto.setDiscountType(entity.getDiscountType());
	        dto.setMinorderAmount(entity.getMinorderAmount());
	        dto.setCreatedtime(entity.getCreatedtime());
	        dto.setExiparydate(entity.getExiparydate());
	        dto.setMaxUsageCount(entity.getMaxUsageCount());
	        return dto;
	    }

	    public Coupons toEntity(CouponDTO dto) {
	        Coupons entity = new Coupons();
	        entity.setCouponid(dto.getCouponid());
	        entity.setCouponcode(dto.getCouponcode());
	        entity.setDecsription(dto.getDescription());
	        entity.setDiscountvalue(dto.getDiscountvalue());
	        entity.setDiscountType(dto.getDiscountType());
	        entity.setMinorderAmount(dto.getMinorderAmount());
	        entity.setCreatedtime(dto.getCreatedtime());
	        entity.setExiparydate(dto.getExiparydate());
	        entity.setMaxUsageCount(dto.getMaxUsageCount());
	        return entity;
	    }
}
