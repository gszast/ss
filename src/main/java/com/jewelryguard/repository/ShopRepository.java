package com.jewelryguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jewelryguard.model.Shop;
import com.jewelryguard.model.User;

@Repository("ShopRepository")
public interface ShopRepository extends JpaRepository<Shop, Integer> {
	 Shop findByOwner(User user);
}
