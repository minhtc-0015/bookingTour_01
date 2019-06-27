package app.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.User;
import app.service.UserService;

@Controller
public class AuthController {
	public UserService getUserService() {
		return userService;
	}
	
	@Autowired
    private UserService userService;
	
	private Properties getProperties() {
		Properties prop = new Properties();
		 try (InputStream input = new FileInputStream("src/main/resources/messages.properties")) {
	            prop.load(input);

	            return prop;
		    } catch (IOException ex) {
	            ex.printStackTrace();
	        }
            return prop;
	}
	
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
		Properties prop = getProperties();
		modelMap.put("message_login", prop.getProperty("error.login"));
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("current_user");
		return "redirect:/index";
	}
}
