package app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import app.model.Car;
import app.service.CarService;

public class CarServiceImpl extends BaseServiceImpl implements CarService {
	private static final Logger logger = Logger.getLogger(CarServiceImpl.class);

	@Override
	public Car saveOrUpdate(Car entity) {
		try {
			return getCarDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean deleteCar(Integer id) {
		try {
			Car car = getCarDAO().findById(id);
			return delete(car);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<Car> loadCars() {
		try {
			return getCarDAO().loadCars();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Car findById(Serializable key) {
		try {
			return getCarDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Car entity) {
		try {
			getCarDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
}
