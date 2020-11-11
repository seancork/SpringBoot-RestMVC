package ie.son.services;

import ie.son.entities.User;

public interface UserService {
	User findUser(int id);
	User findByUserId(int JobId);
	User save(User user);
	User findByEmail(String email);
}
