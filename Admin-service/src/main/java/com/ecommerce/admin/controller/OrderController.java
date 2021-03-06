package com.ecommerce.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.admin.entity.Order;
import com.ecommerce.admin.header.HeaderGenerator;
import com.ecommerce.admin.service.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This order controller consists of api's to get the all orders based on order
 * id, user id.
 */
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private HeaderGenerator headerGenerator;

	/** This is used to get a specified order based on id
	 * @param id
	 * @return Order Instance
	 */
	@ApiOperation(value = "Getting order details by order id")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = String.class, message = "retrived successfully"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, response = String.class, message = "wrong parameters") })
	@GetMapping(value = "/order/{id}")
	public ResponseEntity<Order> getReceiptById(@PathVariable("id") long id) {
		Order order = orderService.getOrderById(id);
		if (order != null) {
			return new ResponseEntity<Order>(order, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<Order>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	/** This is used to get orders based on user id
	 * @param userId
	 * @return List
	 */
	@ApiOperation(value = "Getting single user orders")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = String.class, message = "retrived successfully"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, response = String.class, message = "wrong parameters") })
	@GetMapping(value = "/orders/{userId}", params = "userId")
	public ResponseEntity<List<Order>> getAllOrdersByUserId(@RequestParam("userId") Long userId) {
		List<Order> products = orderService.getAllOrdersById(userId);
		if (!products.isEmpty()) {
			return new ResponseEntity<List<Order>>(products, headerGenerator.getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Order>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	/** This is used to get all the orders
	 * @return List
	 */
	@ApiOperation(value = "Getting All order ")
	@ApiResponses(value = {
			@ApiResponse(code = HttpServletResponse.SC_OK, response = String.class, message = "retrived successfully"),
			@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, response = String.class, message = "wrong parameters") })
	@GetMapping(value = "/orders")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> products = orderService.getAllOrders();
		if (!products.isEmpty()) {
			return new ResponseEntity<List<Order>>(products, headerGenerator.getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Order>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

}