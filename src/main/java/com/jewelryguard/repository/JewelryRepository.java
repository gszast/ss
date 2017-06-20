package com.jewelryguard.repository;

import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JewelryRepository")
public interface JewelryRepository extends PagingAndSortingRepository<Jewelry, Integer> {
	 Page<Jewelry> findAllByUser(User user, Pageable pageableRequest);
}
