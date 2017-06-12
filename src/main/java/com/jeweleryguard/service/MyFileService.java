package com.jeweleryguard.service;

import com.jeweleryguard.model.MyFile;

import java.io.File;
import java.util.List;

public interface MyFileService {
    public MyFile findByName(String name);
    public MyFile save(MyFile file);
	public List<MyFile> findAll();
    public byte[] write(File file);
    public byte[] read(File file);
}
