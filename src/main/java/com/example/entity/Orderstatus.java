package com.example.entity;

public enum Orderstatus {
	    PLACED,             // Order placed but not yet proceesed.
	    CONFIRMED,           // Order has been confirmed
	    PROCESSING,          // Items are being prepared (picked/packed)
	    SHIPPED,             // Order has been shipped by the seller
	    OUT_FOR_DELIVERY,    // Order is with the delivery agent
	    DELIVERED,           // Successfully delivered to the customer
	    CANCELLED,           // Order was cancelled before shipping
	    RETURN_REQUESTED,    // Customer initiated return
	    RETURNED,            // Order has been returned
	    REFUNDED             //Money has been refunded
}
