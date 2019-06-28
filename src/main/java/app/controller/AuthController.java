package app.controller;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.controller.admin.BaseController;
import app.model.User;
import app.service.UserService;

@Controller
public class AuthController extends BaseController{
	public UserService getUserService() {
		return userService;
	}
	
	@Autowired
    private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Map<String, Object> model) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session, ModelMap modelMap, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
            return "login";
        }
		User user1 = getUserService().checkLogin(user.getUsername(), user.getPassword());
		if(user1 != null) {
			session.setAttribute("current_user", user1.getUsername());
			return "index";
		}
		
		modelMap.put("message_login", getProperties().getProperty("error.login"));
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("current_user");
		return "redirect:/index";
	}
}
