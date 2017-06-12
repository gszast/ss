package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Attachment;
import com.jeweleryguard.model.Jewelry;

public interface AttachmentService {
    public Attachment findByName(String name);

    public Attachment findByJewelry(Jewelry jewelry);
    public Attachment save(Attachment attachment);
	public List<Attachment> findAll();
}
