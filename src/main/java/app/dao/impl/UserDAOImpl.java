package app.dao.impl;

import java.util.List;

import app.dao.CarDAO;
import app.dao.GenericDAO;
import app.dao.UserDAO;
import app.model.Car;
import app.model.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadUsers() {
		return getSession().createQuery("from User").getResultList();
	}

}
