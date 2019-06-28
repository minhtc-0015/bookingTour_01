package app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import app.model.Car;
import app.model.TourDetails;

@Component
public class TourDeatailsValidator {

	public boolean validate(Object o, Errors errors) {
		TourDetails tourdetail = (TourDetails) o;
		Boolean logic = false;

		if (tourdetail.getTourDetailsNumPerson() <= 0) {
			errors.rejectValue("tourDetailsNumPerson", "error.tourDetailsNumPerson");
			logic = true;
		}

		return logic;
	}
}