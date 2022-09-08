package me.chrisanabo.model.validation;


import me.chrisanabo.model.util.DateTimeUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DateFormatValidator implements ConstraintValidator<DateFormatCheck, String> {

	@Override
	public boolean isValid(String dateTime, ConstraintValidatorContext context) {
		return DateTimeUtils.isValidDateTime(dateTime);
	}
}
