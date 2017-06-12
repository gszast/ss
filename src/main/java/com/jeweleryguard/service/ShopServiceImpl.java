package com.jeweleryguard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeweleryguard.model.Shop;
import com.jeweleryguard.model.User;
import com.jeweleryguard.repository.ShopRepository;

@Service("ShopService")
public class ShopServiceImpl implements ShopService{

    @Autowired
    private ShopRepository shopRepository;

    public Shop findByOwner(User user) {
        return shopRepository.findByOwner(user);
    }

    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
        
    }
    public List<Shop> findAll(){
    	return shopRepository.findAll();
    }
}
