package com.jeweleryguard.service;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;
import com.jeweleryguard.repository.JewelryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("JewelryService")
public class JewelryServiceImpl implements JewelryService{

    @Override
	public List<Jewelry> findAll() {
		return jewelryRepository.findAll();
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
