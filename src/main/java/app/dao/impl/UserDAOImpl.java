package app.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import app.dao.GenericDAO;
import app.dao.UserDAO;
import app.model.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {

	
	
	public UserDAOImpl() {
		super(User.class);
	}
	
	public UserDAOImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadUsers() {
		return getSession().createQuery("from User").getResultList();
	}
	
	@Override
	public User findByUser(String username) {
	    return (User) getSession().createQuery("from User where username=:username")
				.setParameter("username", username).uniqueResult();
	}

}
