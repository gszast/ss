package com.jewelryguard.service;
import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.User;
import com.jewelryguard.repository.JewelryRepository;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

;

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
    public Page<Jewelry> findAllByUser(User user, Pageable pageableRequest) {
        return jewelryRepository.findAllByUser(user, pageableRequest);
    }

    @Override
    public void saveJewelry(Jewelry jewelry) {
        jewelryRepository.save(jewelry);
    }

}
