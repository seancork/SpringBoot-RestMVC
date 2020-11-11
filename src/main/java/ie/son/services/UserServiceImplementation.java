package ie.son.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.son.dao.UserDao;
import ie.son.entities.User;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImplementation implements UserService {

	@Autowired 
	UserDao userDao;
	
	@Override
	public User findUser(int id) {
		if (userDao.existsById(id))
			return userDao.findById(id).get();
		return null;
	}

	@Override
	public User findByUserId(int userId) {
		if (userDao.existsById(userId))
			return userDao.findByUserId(userId);
		return null;
	}

	@Override
	public User save(User user) {
		log.info(user.toString());
		
		if (userDao.existsByEmail(user.getEmail()))
			return null;
		return userDao.save(user);
	}

	@Override
	public User findByEmail(String email) {
		if (userDao.existsByEmail(email))
			return userDao.findByEmail(email);
		return null;
	}
}
