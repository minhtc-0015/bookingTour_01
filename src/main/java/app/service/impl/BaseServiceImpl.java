package app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import app.dao.CarDAO;
import app.dao.RoleDAO;
import app.dao.UserDAO;

public class BaseServiceImpl {
	
	@Autowired
	protected CarDAO carDAO;
	
	@Autowired
	protected UserDAO userDAO;
	
	@Autowired
	protected RoleDAO roleDAO;

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public CarDAO getCarDAO() {
		return carDAO;
	}

	public void setCarDAO(CarDAO carDAO) {
		this.carDAO = carDAO;
	}
}
