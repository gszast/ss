package com.jeweleryguard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeweleryguard.model.Attachment;
import com.jeweleryguard.model.File;
import com.jeweleryguard.repository.FileRepository;

@Service("FileService")
public class FileServiceImpl implements FileService{

	@Override
	public File findByAttachment(Attachment attachment) {
		return fileRepository.findByAttachment(attachment);
	}

	@Override
	public List<File> findAll() {
		return fileRepository.findAll();	
		}

	@Override
	public File findByName(String name) {
		return fileRepository.findByName(name);
	}

	@Override
	public File save(File file) {
		return fileRepository.save(file);
	}

	@Autowired
	private FileRepository fileRepository;
	
	

}
