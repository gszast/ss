package com.jeweleryguard.repository;

import com.jeweleryguard.model.MyFile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("MyFileRepository")
public interface MyFileRepository extends PagingAndSortingRepository<MyFile, Integer>{
	public MyFile findByName(String name);
}
