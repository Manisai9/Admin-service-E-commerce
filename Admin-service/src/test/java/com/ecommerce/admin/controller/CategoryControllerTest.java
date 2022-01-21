package com.ecommerce.admin.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.admin.entity.Category;
import com.ecommerce.admin.header.HeaderGenerator;
import com.ecommerce.admin.service.CategoryService;

@SpringBootTest(classes = { CategoryControllerTest.class })
public class CategoryControllerTest {

	@Mock
	private CategoryService categoryService;

	@Mock
	private HeaderGenerator headerGenerator;

	@InjectMocks
	CategoryController categoryController;

	List<Category> categories;

	Category category = new Category(1L, "Electronics", "All Laptops");

	@Test
	public void testAddCategory() {

		when(categoryService.addCategory(category)).thenReturn(category);

		ResponseEntity<Category> response = categoryController.addCategory(category, null);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(category, response.getBody());

		ResponseEntity<Category> res = categoryController.addCategory(null, null);
		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
		assertEquals(null, res.getBody());
	}

	@Test
	public void testUpdateCategory() {

		when(categoryService.updateCategory(category, null)).thenReturn(category);

		ResponseEntity<Category> response = categoryController.updateCategory(category, null, null);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(category, response.getBody());

		ResponseEntity<Category> res = categoryController.updateCategory(null, null, null);
		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
		assertEquals(null, res.getBody());
	}

	@Test
	public void testDeleteCategory() {

		when(categoryService.deleteCategory(category.getId())).thenReturn("Deleted");

		ResponseEntity<String> response = categoryController.deleteCategory(category.getId());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Deleted", response.getBody());
	}

	@Test
	public void testGetAllCategories() {
		categories = new ArrayList<Category>();
		categories.add(new Category(1L, "Electronics", "All Laptops"));
		categories.add(new Category(1L, "Mobiles", "All types of mobiles"));
		when(categoryService.getAllCategory()).thenReturn(categories);
		ResponseEntity<List<Category>> response = categoryController.getAllCategory();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, response.getBody().size());

	}

}
