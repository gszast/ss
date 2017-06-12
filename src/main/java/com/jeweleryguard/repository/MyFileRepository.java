package com.jeweleryguard.repository;

import com.jeweleryguard.model.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("MyFileRepository")
public interface MyFileRepository extends JpaRepository<MyFile, Integer>{
	public MyFile findByName(String name);
}
