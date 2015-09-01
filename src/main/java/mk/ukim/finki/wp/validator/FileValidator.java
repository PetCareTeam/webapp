package mk.ukim.finki.wp.validator;

import mk.ukim.finki.wp.model.PetUser;
import mk.ukim.finki.wp.model.PictureUpload;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {
	public boolean supports(Class<?> paramClass) {
		return PetUser.class.equals(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		PictureUpload file = (PictureUpload) obj;
		  if (file.getFile().getSize() == 0) {
		   errors.rejectValue("register", "valid.file");
		  }
	}
}