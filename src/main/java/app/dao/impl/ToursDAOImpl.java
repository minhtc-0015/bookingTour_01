package app.dao.impl;

import java.util.List;

import org.hibernate.criterion.Projections;

import app.dao.GenericDAO;
import app.dao.ToursDao;
import app.helper.Constants;
import app.model.Tours;

public class ToursDAOImpl extends GenericDAO<Integer, Tours> implements ToursDao {

	public ToursDAOImpl() {
		super(Tours.class);

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Tours> listTours(Integer offset, Integer maxResults) {
		return getSession().createCriteria(Tours.class).setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : Constants.PAGESIZE).list();
	}
	
	@Override
	public List<Tours> listTours() {
		return getSession().createQuery("from Tours",Tours.class).getResultList();
	}

	@SuppressWarnings("deprecation")
	@Override
	public long countTour() {
		return (long) getSession().createCriteria(Tours.class).setProjection(Projections.rowCount()).uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tours> listSaleOffTours() {
		return getSession().createQuery("from Tours as T ORDER BY T.tourSaleOff DESC").getResultList();
	}

	public Tours maxSaleOffTours() {
		return (Tours) getSession().createQuery("from Tours as T ORDER BY T.tourSaleOff DESC").getResultList().get(0);
	}

}