package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Metal;

public interface MetalService {
    Metal findByName(String name);
    Metal save(Metal mmetal);
	public List<Metal> findAll();
}
