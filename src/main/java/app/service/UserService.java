package app.service;

import java.util.List;

import app.model.Car;
import app.model.User;


public interface UserService extends BaseService<Integer, User> {
	boolean deleteUser(Integer id);

	List<User> loadUsers();
	User findByUser(String username);
	User checkLogin(String username, String password);
	
	
}
