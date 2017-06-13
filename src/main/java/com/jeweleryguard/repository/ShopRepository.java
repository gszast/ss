package com.jeweleryguard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jeweleryguard.model.Shop;
import com.jeweleryguard.model.User;

@Repository("ShopRepository")
public interface ShopRepository extends PagingAndSortingRepository<Shop, Long> {
	 Shop findByOwner(User user);
}
