package com.ecommerce.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.entity.Seller;
import com.ecommerce.admin.repository.SellerRepository;

/** 
 * Seller Service Class
 * @author saipavan
 */
@Service
@Transactional
public class SellerService {

	@Autowired
	private SellerRepository repository;
	
	/** 
	 * This method is to add seller to DB
	 * @param seller
	 * @return seller instance
	 */
	public Seller addSeller(Seller seller) {
		return repository.save(seller);
	}
	
	/** 
	 * This method is to view all sellers
	 * @return List
	 */
	public List<Seller> getAllSellers() {
		return repository.findAll();
	}
	
	/** 
	 * This method is to update seller details and save in DB
	 * @param seller
	 * @return seller instance
	 */
	public Seller updateSeller(Seller seller) {
		Seller newSeller = null;
		List<Seller> sellers = repository.findAll();
		for(Seller seller1: sellers)
			if(seller1.getId()==seller.getId())
				newSeller=seller1;
		newSeller.setName(seller.getName());
		newSeller.setAddress(seller.getAddress());
		repository.save(newSeller);
		return newSeller;
	}
	
	/** 
	 * This method will delete seller from DB
	 * @param id
	 * @return message
	 */
	public String deleteSeller(Long id) {
		String str = "";
		if (id != null) {
			repository.deleteById(id);
			str = "Seller Deleted";
		}
		return str;
	}
}
