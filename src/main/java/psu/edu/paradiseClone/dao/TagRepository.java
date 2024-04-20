package psu.edu.paradiseClone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {

	List<Tag> findByImageIdNotNull();
}
