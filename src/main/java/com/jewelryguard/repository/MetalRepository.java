package com.jewelryguard.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jewelryguard.model.Metal;

@Repository("MetalRepository")
public interface MetalRepository extends PagingAndSortingRepository<Metal, Integer>{
	public Metal findByName(String name);
}
