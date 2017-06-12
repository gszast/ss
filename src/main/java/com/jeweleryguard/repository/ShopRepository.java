package com.jeweleryguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeweleryguard.model.Shop;
import com.jeweleryguard.model.User;

@Repository("ShopRepository")
public interface ShopRepository extends JpaRepository<Shop, Long> {
	 Shop findByOwner(User user);
}
