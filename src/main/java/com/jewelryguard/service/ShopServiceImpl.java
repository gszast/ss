package com.jewelryguard.service;

import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewelryguard.model.Shop;
import com.jewelryguard.model.User;
import com.jewelryguard.repository.ShopRepository;

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
    	return Lists.newArrayList( shopRepository.findAll());
    }
}
