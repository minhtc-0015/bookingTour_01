package app.service;

import java.util.List;

import app.model.Car;


public interface CarService extends BaseService<Integer, Car> {
	boolean deleteCar(Integer id);

	List<Car> loadCars();
}
