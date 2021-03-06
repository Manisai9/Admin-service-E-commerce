package com.ecommerce.admin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.admin.entity.Order;

/**
 * Order Repository Interface extends JpaRepository
 * @author saipavan
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {
	
	/**
	 * This is used to get all order of user by Id
	 * @param id
	 * @return List
	 */
	List<Order> findAllOrdersByUserId(Long id);

	Order getById(Long id);

	

}

