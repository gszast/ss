package com.jewelryguard.service;

import com.google.common.collect.Lists;
import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.User;
import com.jewelryguard.repository.JewelryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JewelryService")
public class JewelryServiceImpl implements JewelryService{

    @Override
	public List<Jewelry> findAll() {
		return Lists.newArrayList(jewelryRepository.findAll());
	}

    @Override
    public Jewelry findOne(long jewelryId) {
        return null;
    }

    @Autowired
    private JewelryRepository jewelryRepository;

    @Override
    public Jewelry findJewelryByUser(User user) {
        return jewelryRepository.findByUser(user);
    }

    @Override
    public void saveJewelry(Jewelry jewelry) {
        jewelryRepository.save(jewelry);
    }

}
