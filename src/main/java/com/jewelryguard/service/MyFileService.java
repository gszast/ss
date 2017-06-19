package com.jewelryguard.service;

import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.MyFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public interface MyFileService {
    public List<MyFile> findAll();

    MyFile saveJewelryImage(MultipartFile file, Jewelry jewelry) throws FileNotFoundException;
//
//    HashMap<String, String> findAllByJewelry(Jewelry jewelry);

    List<MyFile> findAllByJewelry(Jewelry jewelry);

    MyFile findOne(int imgId);

    void delete(MyFile myFile);
}