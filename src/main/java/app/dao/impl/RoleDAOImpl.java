package app.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import app.dao.GenericDAO;
import app.dao.RoleDAO;
import app.model.Role;

public class RoleDAOImpl extends GenericDAO<Integer, Role> implements RoleDAO {
	@Autowired
    private RoleDAO roleDAO;
	
	public RoleDAOImpl() {
		super(Role.class);
	}
	
	public RoleDAOImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}
}
