package app.util;

import java.util.HashSet;
import java.util.Set;

import app.model.Role;

public class RoleUtil {
	public Set<Role> roleUtil(int id, String role) {
		Set<Role> set = new HashSet<Role>();
		set.add(new Role(id, role));
		return set;
	}
}
