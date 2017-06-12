package com.jeweleryguard.repository;

import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("JewelryRepository")
public interface JewelryRepository extends JpaRepository<Jewelry, Long> {
	 Jewelry findByUser(User user);
}
