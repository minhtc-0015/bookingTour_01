package app.dao.impl;

import java.util.List;

import app.dao.CarDAO;
import app.dao.GenericDAO;
import app.model.Car;

public class CarDAOImpl extends GenericDAO<Integer, Car> implements CarDAO {

	public CarDAOImpl() {
		super(Car.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Car> loadCars() {
		return getSession().createQuery("from Car").getResultList();
	}

}
