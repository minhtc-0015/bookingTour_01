package app.dao.impl;

import java.util.List;

import app.dao.GenericDAO;
import app.dao.TourDetailsDao;
import app.model.TourDetails;

public class TourDetailsDAOImpl extends GenericDAO<Integer, TourDetails> implements TourDetailsDao {
	public TourDetailsDAOImpl() {
		super(TourDetails.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TourDetails> listTourDetailsById(Integer ids) {
		return getSession().createQuery("FROM TourDetails as p WHERE p.tour.tourId = :ids").setParameter("ids", ids).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TourDetails> listTourDetails() {
		return getSession().createQuery("FROM TourDetails").getResultList();
	}
}