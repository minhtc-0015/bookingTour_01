package app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.service.ToursService;

@RequestMapping("tours")
@Controller
public class ToursController {
	@Autowired
	private ToursService toursService;
	
}