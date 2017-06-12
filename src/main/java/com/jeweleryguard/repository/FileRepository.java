package com.jeweleryguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeweleryguard.model.Attachment;
import com.jeweleryguard.model.File;

@Repository("FileRepository")
public interface FileRepository extends JpaRepository<File, Integer>{
	public File findByName(String name);
	public File findByAttachment(Attachment attachment);
}
