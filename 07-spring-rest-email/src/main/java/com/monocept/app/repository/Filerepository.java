package com.monocept.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.app.model.ImageStructure;

public interface Filerepository extends JpaRepository<ImageStructure,Integer> {

	ImageStructure findByName(String fileName);
	
	

}
