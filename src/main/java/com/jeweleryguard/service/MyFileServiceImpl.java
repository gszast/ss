package com.jeweleryguard.service;

import com.jeweleryguard.model.MyFile;
import com.jeweleryguard.repository.MyFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service("MyFileService")
public class MyFileServiceImpl implements MyFileService{
	@Override
	public byte[] write(File file) {
		return new byte[0];
	}

	@Override
	public byte[] read(File file) {
		return new byte[0];
	}

	@Override
	public List<MyFile> findAll() {
		return fileRepository.findAll();	
		}

	@Override
	public MyFile findByName(String name) {
		return fileRepository.findByName(name);
	}

	@Override
	public MyFile save(MyFile file) {
		return fileRepository.save(file);
	}

	@Autowired
	private MyFileRepository fileRepository;
	
	

}
