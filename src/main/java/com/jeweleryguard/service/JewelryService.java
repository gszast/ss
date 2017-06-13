package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;

public interface JewelryService {
    Jewelry findJewelryByUser(User user);
    void saveJewelry(Jewelry jewelry);
	public List<Jewelry> findAll();
}
