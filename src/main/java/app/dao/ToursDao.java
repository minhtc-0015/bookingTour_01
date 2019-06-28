package app.dao;

import java.util.List;

import app.model.Tours;

public interface ToursDao extends BaseDAO<Integer, Tours> {
	List<Tours> listTours(Integer offset, Integer maxResults);
	List<Tours> listTours();
	public long countTour();
	List<Tours> listSaleOffTours();
	Tours maxSaleOffTours();
}