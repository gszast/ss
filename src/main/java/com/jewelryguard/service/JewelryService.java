package com.jewelryguard.service;

import java.util.List;

import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JewelryService {
//    Page<Jewelry> findAllByUser(User user);

    Page<Jewelry> findAllByUser(User user, Pageable pageableRequest);

    void saveJewelry(Jewelry jewelry);
	public List<Jewelry> findAll();

    Jewelry findOne(int jewelryId);

    boolean delete(Jewelry jewelry);
}
