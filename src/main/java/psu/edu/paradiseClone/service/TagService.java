package psu.edu.paradiseClone.service;

import java.util.List;

import psu.edu.paradiseClone.entity.Tag;

public interface TagService {
	
	Tag findByID(int id);
	List<Tag> findByImageIdNotNull();
	List<Tag> findAll();

}
