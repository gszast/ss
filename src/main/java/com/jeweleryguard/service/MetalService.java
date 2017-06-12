package com.jeweleryguard.service;

import java.util.List;

import com.jeweleryguard.model.Metal;

public interface MetalService {
    public Metal findByName(String name);
    public Metal save(Metal mmetal);
	public List<Metal> findAll();
}
