package com.jewelryguard.repository;

import com.jewelryguard.model.MyFile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("MyFileRepository")
public interface MyFileRepository extends PagingAndSortingRepository<MyFile, Integer>{
}
