package com.jeweleryguard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeweleryguard.model.Attachment;
import com.jeweleryguard.model.Category;
import com.jeweleryguard.model.Jewelry;

@Repository("AttachmentRepository")
public interface AttachmentRepository extends JpaRepository<Attachment, Integer>{
	public Attachment findByName(String name);

	public Attachment findByJewelry(Jewelry jewelry);
}
