package com.jeweleryguard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeweleryguard.model.Metal;
import com.jeweleryguard.repository.MetalRepository;

@Service("MetalService")
public class MetalServiceImpl implements MetalService{

	@Override
	public List<Metal> findAll() {
		return metalRepository.findAll();
	}

	@Override
	public Metal findByName(String name) {
		return metalRepository.findByName(name);
	}

	@Override
	public Metal save(Metal metal) {
		return metalRepository.save(metal);
	}

	@Autowired
	private MetalRepository metalRepository;
	
	

}
