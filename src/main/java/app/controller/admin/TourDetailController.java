package app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.TourDetails;
import app.model.Tours;
import app.service.TourDetailsService;
import app.service.ToursService;
import app.validator.TourDeatailsValidator;

@RequestMapping("admin/tourdetails/")
@Controller
public class TourDetailController extends BaseController {
	@Autowired
	private TourDetailsService tourDetailsService;
	@Autowired
	private ToursService toursService;
	@Autowired
	private TourDeatailsValidator tourDeatailsValidator;

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("admin/tourdetails/index");
		List<TourDetails> tourDetails = tourDetailsService.loadTourDetails();
		mav.addObject("tourDetails", tourDetails);
		return mav;
	}

	@GetMapping("/tourdetail")
	public ModelAndView showTourDetailsByTourId(@RequestParam int id) {
		ModelAndView mav = new ModelAndView("admin/tourdetails/index");
		List<TourDetails> tourDetails = tourDetailsService.loadTourDetailsById(id);
		mav.addObject("tourDetails", tourDetails);
		return mav;
	}

	@RequestMapping(value = "/new")
	public ModelAndView newTourDetails(Map<String, Object> model) {
		List<Tours> tours = toursService.loadTours();
		ModelAndView mav = new ModelAndView("admin/tourdetails/add");
		mav.addObject("tours", tours);
		TourDetails tourdetail = new TourDetails();
		model.put("tourdetail", tourdetail);
		return mav;
	}

	@PostMapping("/create")
	public String saveTourDetails(@ModelAttribute("tourdetails") TourDetails tourdetails, BindingResult result,
			SessionStatus status, RedirectAttributes redirectAttributes) {

		if (tourDeatailsValidator.validate(tourdetails, result)) {
			return "admin/tourdetails/add";
		}

		status.setComplete();
		tourDetailsService.saveOrUpdate(tourdetails);
		redirectAttributes.addFlashAttribute("message", getProperties().getProperty("sucess.saveTourDetails"));
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/admin/tourdetails/new/";
	}

	@RequestMapping("/edit")
	public ModelAndView editTourDetails(@RequestParam int id, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("admin/tourdetails/edit");
		TourDetails tourdetail = tourDetailsService.findById(id);

		if (tourdetail == null) {
			redirectAttributes.addFlashAttribute("message", getProperties().getProperty("error.findTourDetails"));
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return mav;
		}
		List<Tours> tours = toursService.loadTours();
		mav.addObject("tourdetail", tourdetail);
		mav.addObject("tours", tours);
		return mav;
	}

	@GetMapping("/delete{id}")
	public String deleteCustomerForm(@RequestParam int id, RedirectAttributes redirectAttributes) {
		tourDetailsService.deleteTourDetails(id);
		redirectAttributes.addFlashAttribute("message", getProperties().getProperty("sucess.deleteTourDetails"));
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/admin/tourdetails/";
	}
}