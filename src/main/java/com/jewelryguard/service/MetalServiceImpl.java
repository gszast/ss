package com.jewelryguard.service;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewelryguard.model.Metal;
import com.jewelryguard.repository.MetalRepository;

@Service("MetalService")
public class MetalServiceImpl implements MetalService{

	@Override
	public List<Metal> findAll() {
		return Lists.newArrayList( metalRepository.findAll());
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
