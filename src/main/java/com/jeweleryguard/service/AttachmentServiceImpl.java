package com.jeweleryguard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeweleryguard.model.Attachment;
import com.jeweleryguard.model.Jewelry;
import com.jeweleryguard.repository.AttachmentRepository;

@Service("AttachmentService")
public class AttachmentServiceImpl implements AttachmentService{

	@Override
	public Attachment findByJewelry(Jewelry jewelry) {
		return attachmentRepository.findByJewelry(jewelry);
	}

	@Override
	public List<Attachment> findAll() {
		return attachmentRepository.findAll();	
		}

	@Override
	public Attachment findByName(String name) {
		return attachmentRepository.findByName(name);
	}

	@Override
	public Attachment save(Attachment attachment) {
		return attachmentRepository.save(attachment);
	}

	@Autowired
	private AttachmentRepository attachmentRepository;
	
	

}
