package com.jewelryguard.repository;

import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MyFileRepository")
public interface MyFileRepository extends JpaRepository<MyFile, Integer>{
    List<MyFile> findAllByJewelry(Jewelry jewelry);
}
