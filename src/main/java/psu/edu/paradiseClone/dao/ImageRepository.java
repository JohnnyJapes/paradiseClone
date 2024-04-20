package psu.edu.paradiseClone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
