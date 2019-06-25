package app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("admin/users/")
@Controller
public class AdminUserController {

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String users() {
		return "admin/user/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String usersAdd() {
		return "admin/user/add";
	}
}
