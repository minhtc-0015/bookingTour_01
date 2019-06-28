package app.service;

import java.util.List;

import app.model.TourDetails;

public interface TourDetailsService extends BaseService<Integer, TourDetails> {
	boolean deleteTourDetails(Integer id);

	List<TourDetails> loadTourDetailsById(Integer id);

	List<TourDetails> loadTourDetails();
}