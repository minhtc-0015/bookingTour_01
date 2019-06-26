package app.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.Car;
import app.service.CarService;
import app.validator.CarValidator;

@RequestMapping("admin/cars/")
@Controller
public class CarController extends BaseController {

	@Autowired
	private CarService carService;

	@Autowired
	private CarValidator carValidator;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "redirect:/admin/cars/index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("cars", null);

		return "redirect:/admin/cars/index/page/1";
	}

	@RequestMapping(value = { "/index/page/{pageNumber}" }, method = RequestMethod.GET)
	public String showCarPage(HttpServletRequest request, @PathVariable int pageNumber, Model model) {
		PagedListHolder<?> cars = (PagedListHolder<?>) request.getSession().getAttribute("cars");
		List<Car> list = carService.loadCars();

		cars = setPagedListHolder(pageNumber, list, cars);

		model = setModelPagination(pageNumber, list, cars, model);

		request.getSession().setAttribute("cars", cars);

		return "admin/cars/index";
	}

	@RequestMapping(value = "/new")
	public String newCar(Map<String, Object> model) {
		Car car = new Car();
		model.put("car", car);
		return "admin/cars/add";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String saveCar(@ModelAttribute("car") Car car, BindingResult result, SessionStatus status,
			RedirectAttributes redirectAttributes) {

		if (carValidator.validate(car, result)) {
			return "admin/cars/add";
		}

		status.setComplete();
		carService.saveOrUpdate(car);
		redirectAttributes.addFlashAttribute("message", getProperties().getProperty("sucess.saveCar"));
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/admin/cars/new/";
	}

	@RequestMapping("/edit")
	public ModelAndView editCustomerForm(@RequestParam int id, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("admin/cars/edit");
		Car car = carService.findById(id);

		if (car == null) {
			redirectAttributes.addFlashAttribute("message", getProperties().getProperty("error.findCar"));
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return mav;
		}

		mav.addObject("car", car);

		return mav;
	}

	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String deleteCustomerForm(@RequestParam int id, @RequestParam int idPage,
			RedirectAttributes redirectAttributes) {
		carService.deleteCar(id);

		redirectAttributes.addFlashAttribute("message", getProperties().getProperty("sucess.deleteCar"));
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/admin/cars/index/page/" + idPage;
	}

}
