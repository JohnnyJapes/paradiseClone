package psu.edu.paradiseClone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psu.edu.paradiseClone.dao.EmployeeRepository;
import psu.edu.paradiseClone.dao.TagRepository;
import psu.edu.paradiseClone.entity.Tag;

@Service
public class TagServiceImpl implements TagService{
	
	TagRepository tagRepo;

	@Autowired
	public TagServiceImpl(TagRepository tagRepository) {
		this.tagRepo = tagRepository;
	}
	@Override
	public Tag findByID(int id) {
		Optional<Tag> result = tagRepo.findById(id);
		if (result.isPresent()) return  result.get();
		else
			throw new RuntimeException("Did not find tag ID - " +id);
	}

	@Override
	public List<Tag> findByImageIdNotNull() {
		List<Tag> result =  tagRepo.findByImageIdNotNull();
		if (!result.isEmpty()) return  result;
		else
			throw new RuntimeException("No collection have images");
		
	}
	
	@Override
	public List<Tag> findAll(){
		List<Tag> result = tagRepo.findAll();
		
		if(result == null) {
			throw new RuntimeException("No tags found");
		}
		return result;
	}
	
	

}
