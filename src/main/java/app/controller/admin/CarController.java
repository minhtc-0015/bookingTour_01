package app.controller.admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class CarController {

	@Autowired
	private CarService carService;
	
	@Autowired
	private CarValidator carValidator;

	
	public Properties getProperties() {
		Properties prop = new Properties();
		 try (InputStream input = new FileInputStream("src/main/resources/messages.properties")) {
	            prop.load(input);

	            return prop;
		    } catch (IOException ex) {
	            ex.printStackTrace();
	        }
            return prop;
	}
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView home() {
		List<Car> cars = carService.loadCars();
		ModelAndView mav = new ModelAndView("admin/cars/index");
		mav.addObject("cars", cars);
		return mav;
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

         Properties prop = getProperties();
         
		status.setComplete();
		carService.saveOrUpdate(car);
		redirectAttributes.addFlashAttribute("message", prop.getProperty("sucess.saveCar"));
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/admin/cars/new/";
	}

	@RequestMapping("/edit")
	public ModelAndView editCustomerForm(@RequestParam int id,RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("admin/cars/edit");
		Car car = carService.findById(id);			
	    
		Properties prop = getProperties();
		
		if(car == null) {
		    redirectAttributes.addFlashAttribute("message", prop.getProperty("error.findCar"));
		    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		  return mav;  
		}
		
		mav.addObject("car", car);
       
		return mav;
	}

	@RequestMapping("/delete")
	public String deleteCustomerForm(@RequestParam int id,RedirectAttributes redirectAttributes) {
		carService.deleteCar(id);
		
		Properties prop = getProperties();
		
		redirectAttributes.addFlashAttribute("message", prop.getProperty("sucess.deleteCar"));
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/admin/cars/";
	}

}
