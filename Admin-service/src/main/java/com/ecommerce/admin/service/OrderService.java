package com.ecommerce.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.entity.Order;
import com.ecommerce.admin.repository.OrderRepository;

/**
 * Order Service class
 * @author saipavan
 */
@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/** 
	 * To get order details based on order id
	 * @param id
	 * @return order instance
	 */
	public Order getOrderById(Long id) {
		return orderRepository.getById(id);
	}

	/** 
	 * To get order details based on user id
	 * @param id
	 * @return List
	 */
	public List<Order> getAllOrdersById(Long id) {
		return orderRepository.findAllOrdersByUserId(id);
	}

	/** 
	 * To get all the order details
	 * @return List
	 */
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

}