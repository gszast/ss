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
    public Jewelry findOne(int jewelryId) {
        return jewelryRepository.findOne(jewelryId);
    }

    @Override
    public boolean delete(Jewelry jewelry) {
        jewelryRepository.delete(jewelry);
        return true;
    }

    @Autowired
    private JewelryRepository jewelryRepository;

    @Override
    public List<Jewelry> findAllByUser(User user) {
        return jewelryRepository.findAllByUser(user);
    }

    @Override
    public void saveJewelry(Jewelry jewelry) {
        jewelryRepository.save(jewelry);
    }

}
