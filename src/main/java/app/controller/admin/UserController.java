package app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import app.model.User;
import app.service.UserService;

@RequestMapping("admin/users/")
@Controller
public class UserController {

	@Autowired
	private UserService userService; 
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView index() {
		List<User> users = userService.loadUsers();
	    ModelAndView model = new ModelAndView("admin/user/index");
	    model.addObject("users", users);
	    return model;
	}
	
	@RequestMapping(value = "/new")
	public String add(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		return "admin/user/add";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String add(@ModelAttribute("user") User user) {
		userService.saveOrUpdate(user);
		return "redirect:/admin/user/";
	}
}