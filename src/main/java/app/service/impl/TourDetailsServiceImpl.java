package app.service.impl;

import java.io.Serializable;
import java.util.List;

import app.model.TourDetails;
import app.service.TourDetailsService;

public class TourDetailsServiceImpl extends TourDetailsBaseServiceImpl implements TourDetailsService {
	
	@Override
	public List<TourDetails> loadTourDetails() {
		try {
			return getTourDetailsDAO().listTourDetails();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<TourDetails> loadTourDetailsById(Integer ids) {
		try {
			return getTourDetailsDAO().listTourDetailsById(ids);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public TourDetails findById(Serializable key) {
		try {
			return getTourDetailsDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public TourDetails saveOrUpdate(TourDetails entity) {
		try {
			return getTourDetailsDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean deleteTourDetails(Integer id) {
		try {
			TourDetails tours = getTourDetailsDAO().findById(id);
			return delete(tours);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean delete(TourDetails entity) {
		try {
			getTourDetailsDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

}