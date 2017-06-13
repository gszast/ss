package com.jewelryguard.service;

import java.util.List;

import com.jewelryguard.model.Shop;
import com.jewelryguard.model.User;

public interface ShopService {
	public Shop findByOwner(User user);

	public List<Shop> findAll();
	public Shop saveShop(Shop shop);
	
}
