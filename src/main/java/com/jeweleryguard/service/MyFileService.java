package com.jeweleryguard.service;

import com.jeweleryguard.model.MyFile;

import java.io.File;
import java.util.List;

public interface MyFileService {
    MyFile findByName(String name);
    MyFile save(MyFile file);
	public List<MyFile> findAll();
    byte[] writeBase64Image(String file, String fileName);
    byte[] readJewelryImage(File file);

    MyFile saveJeweleryImage(MyFile myFile);
}
