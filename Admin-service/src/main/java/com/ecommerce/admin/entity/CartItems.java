package com.ecommerce.admin.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity Class for Cart Items
 * @author saipavan
 */

@Document(collection = "cart_items")
public class CartItems {
	
	public static final String SEQUENCE_NAME = "cartitems_sequence"; 

	@Id
	private long id;

	@NotNull
	private long productId;

	@NotNull
	private int quantity;

	private float subTotal;

	@NotNull
	private long userId;

	private List<Order> orders;

	public CartItems() {
		super();
	}

	public CartItems(long productId, int quantity, float subTotal, long userId) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.userId = userId;
	}

	public CartItems(long id, long productId, int quantity, float subTotal, long userId, List<Order> orders) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.subTotal = subTotal;
		this.userId = userId;
		this.orders = orders;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(float totalAmount) {
		this.subTotal = totalAmount;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}