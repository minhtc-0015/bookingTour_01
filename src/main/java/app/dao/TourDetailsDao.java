package app.dao;

import java.util.List;

import app.model.TourDetails;

public interface TourDetailsDao extends BaseDAO<Integer, TourDetails> {
	
	List<TourDetails> listTourDetails();

	List<TourDetails> listTourDetailsById(Integer ids);
}