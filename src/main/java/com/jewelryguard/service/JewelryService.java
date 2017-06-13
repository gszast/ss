package com.jewelryguard.service;

import java.util.List;

import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.User;

public interface JewelryService {
    Jewelry findJewelryByUser(User user);
    void saveJewelry(Jewelry jewelry);
	public List<Jewelry> findAll();

    Jewelry findOne(long jewelryId);
}
