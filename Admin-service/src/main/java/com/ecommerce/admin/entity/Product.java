package com.ecommerce.admin.entity;

import java.util.List;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity Class for Product
 * @author saipavan
 */

@Document(collection = "product")
public class Product {
	
	public static final String SEQUENCE_NAME = "products_sequence"; 

	@Id
	private Long id;

	@Size(min = 3, max = 15)
	@NotEmpty(message = "*Please provide your category name")
	private String categoryName;

	@Size(min = 3, max = 15)
	@NotEmpty(message = "*Please provide your product name")
	private String name;

	@Size(min = 3, max = 30)
	@NotEmpty(message = "*Please provide your product description")
	private String description;

	@NotNull
	private float price;

	@NotNull
	private int stock;

	@NotNull
	private Long sellerId;

	private List<Order> orders;

	public Product(Long id, String categoryName, String name, String description, float price, int stock,
			Long sellerId) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.sellerId = sellerId;
	}

	public Product() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", categoryName=" + categoryName + ", name=" + name + ", description="
				+ description + ", price=" + price + ", stock=" + stock + ", sellerId=" + sellerId + "]";
	}

}