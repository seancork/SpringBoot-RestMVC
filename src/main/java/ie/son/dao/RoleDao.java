package ie.son.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.son.entities.Role;

public interface RoleDao extends JpaRepository<Role, Integer> 
{
	boolean existsByUserEmail(String email);
}