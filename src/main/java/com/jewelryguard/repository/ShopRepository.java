package com.jewelryguard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jewelryguard.model.Shop;
import com.jewelryguard.model.User;

@Repository("ShopRepository")
public interface ShopRepository extends PagingAndSortingRepository<Shop, Long> {
	 Shop findByOwner(User user);
}
