package com.jeweleryguard.repository;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("JewelryRepository")
public interface JewelryRepository extends PagingAndSortingRepository<Jewelry, Long> {
	 Jewelry findByUser(User user);
}
