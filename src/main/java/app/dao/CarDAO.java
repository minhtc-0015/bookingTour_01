package app.dao;

import java.util.List;

import app.model.Car;

public interface CarDAO extends BaseDAO<Integer, Car> {
	List<Car> loadCars();
}
