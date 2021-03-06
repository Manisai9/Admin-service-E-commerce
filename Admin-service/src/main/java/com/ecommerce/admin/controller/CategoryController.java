package com.ecommerce.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.admin.entity.Category;
import com.ecommerce.admin.header.HeaderGenerator;
import com.ecommerce.admin.service.CategoryService;
import com.ecommerce.admin.service.SequenceGeneratorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This category controller consists of api's to add,retrieve,update and delete
 * category.
 */
@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private HeaderGenerator headerGenerator;
	
	@Autowired
	SequenceGeneratorService seqGeneratorService;

	/** This is used to view All Categories
	 * @return List
	 */
	@ApiOperation(value = "To view All Categories")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = String.class, message = "view All Category"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, response = String.class, message = "Not Found") })
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategory() {
		List<Category> categorys = categoryService.getAllCategory();
		if (categorys != null) {
			return new ResponseEntity<List<Category>>(categorys, headerGenerator.getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Category>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	/** This is used to add a category
	 * @param addCategory
	 * @param request
	 * @return category Instance
	 */
	@ApiOperation(value = "To add a category")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_CREATED, response = Category.class, message = "category Added successfully"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, response = String.class, message = "unable to add category") })
	@PostMapping("/category/add")
	public ResponseEntity<Category> addCategory(@RequestBody Category addCategory, HttpServletRequest request) {
		addCategory.setId(seqGeneratorService.generateSequence(Category.SEQUENCE_NAME));
		Category newCategory = categoryService.addCategory(addCategory);
		if (newCategory != null) {
			return new ResponseEntity<Category>(newCategory,
					headerGenerator.getHeadersForSuccessPostMethod(request, newCategory.getId()), HttpStatus.CREATED);
		}
		return new ResponseEntity<Category>(headerGenerator.getHeadersForError(), HttpStatus.BAD_REQUEST);
	}

	/** This is used to update a category
	 * @param category
	 * @param id
	 * @param request
	 * @return category Instance
	 */
	@ApiOperation(value = "To update a category")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_CREATED, response = String.class, message = "category updated Successfully"),
			@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, response = String.class, message = "unable to update category"),
			@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response = String.class, message = "somthing went wrong") })
	@PutMapping("/category/update/{id}")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") Long id,
			HttpServletRequest request) {
		if (category != null)
			try {
				categoryService.updateCategory(category, id);
				return new ResponseEntity<Category>(category,
						headerGenerator.getHeadersForSuccessPostMethod(request, category.getId()), HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
	}

	/** This is used to delete a category
	 * @param id
	 * @return message
	 */
	@ApiOperation(value = "To delete a category")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = String.class, message = "category deleted successfully"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, response = String.class, message = "wrong parameters") })
	@DeleteMapping(value = "/category/delete/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") long id) {
		String str = categoryService.deleteCategory(id);
		if (str != null) {
			return new ResponseEntity<String>(str, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<String>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

}