package app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import app.dao.TourDetailsDao;

public class TourDetailsBaseServiceImpl {
	
	@Autowired
	protected TourDetailsDao tourDetailsDAO;

	public TourDetailsDao getTourDetailsDAO() {
		return tourDetailsDAO;
	}

	public void setTourDetailsDAO(TourDetailsDao tourDetailsDAO) {
		this.tourDetailsDAO = tourDetailsDAO;
	}
}