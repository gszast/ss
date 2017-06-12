package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Attachment;
import com.jeweleryguard.model.File;

public interface FileService {
    public File findByName(String name);
    public File findByAttachment(Attachment attachment);
    public File save(File file);
	public List<File> findAll();
}
