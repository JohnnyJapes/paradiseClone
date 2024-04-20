package psu.edu.paradiseClone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findRoleByName(String theRoleName);

}
