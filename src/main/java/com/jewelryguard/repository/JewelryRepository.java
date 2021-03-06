package com.jewelryguard.repository;

import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("JewelryRepository")
public interface JewelryRepository extends JpaRepository<Jewelry, Integer> {
	 Page<Jewelry> findAllByUser(User user, Pageable pageableRequest);
}
