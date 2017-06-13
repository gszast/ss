package com.jewelryguard.service;

import com.jewelryguard.model.Jewelry;
import com.jewelryguard.model.MyFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface MyFileService {
	public List<MyFile> findAll();

    MyFile saveJewelryImage(String imageBase64, Jewelry jewelry ) throws FileNotFoundException;
}
