package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class routeSampleController {

	@RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/tours", method = RequestMethod.GET)
	public String tour() {
		return "tours";
	}

	@RequestMapping(value = "/tour-place", method = RequestMethod.GET)
	public String tourPlace() {
		return "tour-place";
	}

	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public String services() {
		return "services";
	}

	@RequestMapping(value = "/hotels", method = RequestMethod.GET)
	public String hotels() {
		return "hotels";
	}

	@RequestMapping(value = "/hotel-room", method = RequestMethod.GET)
	public String hotelRoom() {
		return "hotel-room";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}
	
	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String blog() {
		return "blog";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		return "contact";
	}
	
	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header() {
		return "static/header";
	}
	
	@RequestMapping(value = "/footer", method = RequestMethod.GET)
	public String footer() {
		return "static/footer";
	}
	/*
	 * @RequestMapping(value = "/hello", method = RequestMethod.GET) public String
	 * hello(@RequestParam(value = "name", required = false, defaultValue = "World")
	 * String name, Model model) { model.addAttribute("name", name); return "hello";
	 * }
	 */
}
