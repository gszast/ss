package com.jewelryguard.service;

import java.util.List;

import com.jewelryguard.model.Metal;

public interface MetalService {
    Metal findByName(String name);
    Metal save(Metal mmetal);
	public List<Metal> findAll();
}
