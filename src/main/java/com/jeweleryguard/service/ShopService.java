package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Shop;
import com.jeweleryguard.model.User;

public interface ShopService {
	public Shop findByOwner(User user);

	public List<Shop> findAll();
	public Shop saveShop(Shop shop);
	
}
