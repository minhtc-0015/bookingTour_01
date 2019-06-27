package app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import app.model.Car;

@Component
public class CarValidator {

	
	
 public boolean validate(Object o,Errors errors) {
    Car car = (Car) o;
    Boolean logic = false;
    
    if (car.getNumberOfSeater() <= 0) {
		errors.rejectValue("numberOfSeater", "error.numberOfSeater");          
        logic = true;
    }

	if (car.getLicensePlate().isEmpty()) {
		errors.rejectValue("licensePlate", "error.licensePlate");
		logic = true;
	}
    return logic;
 }
}
