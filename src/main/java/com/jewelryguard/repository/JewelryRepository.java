package com.jewelryguard.repository;

import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("JewelryRepository")
public interface JewelryRepository extends PagingAndSortingRepository<Jewelry, Long> {
	 Jewelry findByUser(User user);
}
