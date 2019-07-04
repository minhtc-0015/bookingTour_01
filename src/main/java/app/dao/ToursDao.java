package app.dao;

import java.util.List;

import app.model.Tours;

public interface ToursDao extends BaseDAO<Integer, Tours> {
	List<Tours> listTours(String search,Integer offset,Integer maxResults);
	List<Tours> listTours();
	public long countTour(String search);
	List<Tours> listSaleOffTours();
	Tours maxSaleOffTours();
}