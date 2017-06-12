package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;

public interface JewelryService {
    public Jewelry findJewelryByUser(User user);
    public void saveJewelry(Jewelry jewelry);
	public List<Jewelry> findAll();
}
