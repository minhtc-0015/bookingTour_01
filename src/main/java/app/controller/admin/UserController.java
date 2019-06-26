package app.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.User;
import app.service.UserService;

@RequestMapping("admin/users/")
@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService; 
    
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String home() {
		return "redirect:/admin/users/index";
	}
	
	
	@RequestMapping(value = {"/index"}, method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,RedirectAttributes redirect) {

		request.getSession().setAttribute("users", null);
		
		return "redirect:/admin/users/index/page/1";
	}
	
	@RequestMapping(value="/index/page/{pageNumber}",method = RequestMethod.GET)
	public String showUserPage(HttpServletRequest request,@PathVariable int pageNumber,Model model) {
		PagedListHolder<?> users = (PagedListHolder<?>) request.getSession().getAttribute("users");
		List<User> list = userService.loadUsers();
		
		users = setPagedListHolder(pageNumber, list, users);
		
		model = setModelPagination(pageNumber, list, users, model);
		
		// cái này em set sesion để cho thằng pagedListHolder lấy vế chứ không phải view chị ơi
		//,view em set list ueser trong hàm setModelPagiantion rùi
		request.getSession().setAttribute("users", users);
		
		return "admin/user/index";
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
