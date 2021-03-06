package com.ecommerce.admin.entity;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity Class for Category
 * @author saipavan
 */
@Document(collection = "category")
public class Category {
	
	public static final String SEQUENCE_NAME = "category_sequence"; 

	@Id
	private Long id;

	@Size(min = 3, max = 15)
	@NotEmpty(message = "*Please provide your category name")
	private String name;

	@Size(min = 3, max = 30)
	@NotEmpty(message = "*Please provide your category description")
	private String description;

	public Category() {

	}

	public Category(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}