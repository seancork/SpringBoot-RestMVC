package ie.son.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ie.son.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
	User findByUserId(int userId);
	boolean existsByUserId(int userId);
//	boolean existsByCountyName(int i);
	List<User> findAllByOrderByNameAsc();
	 @Query("SELECT c.name FROM User c where c.userId = :id") 
	 String findNameOfCountyById(@Param("id") int id);
		boolean existsByEmail(String email);
		
		User findByEmail(String email);

}
