package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ProductDto;
import com.example.entity.Orderitems;
import com.example.entity.Orders;
import com.example.entity.Orderstatus;
import com.example.feignsss.CartService;
import com.example.feignsss.Productservice;
import com.example.feignsss.UserService;
import com.example.repo.CouponsRepository;
import com.example.repo.OrderRepository;

import feign.FeignException;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
    private OrderRepository orderRepository;
	 @Autowired
	    private Productservice productClient;
	 @Autowired
	 private CouponInterfaceImpl couponservice;
	 @Autowired
	 private UserService userservice;
	 @Autowired
	 private CartService cartclinet;
    @Override
    public Orders createOrder(Orders order) {
    	if (order.getUserid() == null) {
            throw new IllegalArgumentException("User ID must not be null.");
        }
    	try {
    	    userservice.getUserByid(order.getUserid()); // Should throw 404 if user not found
    	} catch (FeignException.NotFound e) {
    	    throw new IllegalArgumentException("User with ID " + order.getUserid() + " not found.");
    	}
    	order.setCreatedat(LocalDate.now()); // Always set creation time
        order.setOrderstatus(Orderstatus.PLACED);
        double totalAmount = 0;
        double totalGst = 0;
    	  if (order.getOrderItems() != null) {
    	        for (Orderitems item : order.getOrderItems()) {
    	        	  if (item.getProductid() == null) {
    	        	        throw new IllegalArgumentException(" Product ID is missing in one of the order items.");
    	        	    }
    	        	  try {
    	        		    cartclinet.getCartByIdAndUserId(order.getUserid());
    	        		} catch (FeignException.NotFound e) {
    	        		    throw new IllegalArgumentException("❌ Cart with ID " + order.getCartid() +
    	        		                                       " not found for User ID: " + order.getUserid());
    	        		}
    	            item.setOrders(order);
    	            ProductDto product;
    	            try {
    	            product = productClient.getProductbyId(item.getProductid());
    	            } catch(FeignException.NotFound e) {
    	            	 throw new IllegalArgumentException("❌ Product not found for ID: " + item.getProductid());
    	            }
                    // ✅ Set price from product service (ensure DTO has `price`)
                    item.setPrice(product.getPrice());

                    double itemTotal = product.getPrice() * item.getQuantity();

                    // ✅ GST based on product category (assume Product has category & gstRate)
                    double itemGst = itemTotal * product.getGstRate() / 100;

                    totalAmount += itemTotal;
                    totalGst += itemGst;
    	        }
    	    }
    	  if (order.getCoupon() != null && order.getCoupon().getCouponcode() != null && 
    			    !order.getCoupon().getCouponcode().isEmpty()) {
    			    try {
    			        // Apply coupon discount via service using coupon code
    			        totalAmount = couponservice.applyCoupon(order.getCoupon().getCouponcode(), totalAmount);
    			    } catch (IllegalArgumentException e) {
    			        // Optional: handle or log invalid coupon
    			        throw new RuntimeException("Invalid coupon: " + e.getMessage());
    			    }
    			}

    			order.setTotalamount(totalAmount); // ✅ Save discounted total
    			order.setGst(totalGst);  

    	 
    	
        return orderRepository.save(order);
    }

    @Override
    public Orders getOrderById(Integer orderid) {
        return orderRepository.findById(orderid).orElse(null);
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Integer orderid) {
        orderRepository.deleteById(orderid);
    }

}
