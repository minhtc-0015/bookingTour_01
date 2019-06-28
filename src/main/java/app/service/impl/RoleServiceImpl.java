package app.service.impl;

import java.io.Serializable;

import org.apache.log4j.Logger;

import app.model.Role;
import app.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Override
	public Role findById(Serializable key) {
		try {
			return getRoleDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Role saveOrUpdate(Role entity) {
		try {
			return getRoleDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(Role entity) {
		try {
			getRoleDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
}
