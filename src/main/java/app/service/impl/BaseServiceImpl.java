package app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import app.dao.CarDAO;

public class BaseServiceImpl {
	
	@Autowired
	protected CarDAO carDAO;

	public CarDAO getCarDAO() {
		return carDAO;
	}

	public void setCarDAO(CarDAO carDAO) {
		this.carDAO = carDAO;
}
}
