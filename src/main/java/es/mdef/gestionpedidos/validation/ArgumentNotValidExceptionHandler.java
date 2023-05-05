package es.mdef.gestionpedidos.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ArgumentNotValidExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex){
			System.err.println("Generando excepción");
			Map<String, String> error = new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((err)-> {
				String fieldName =((FieldError) err).getField();
				String errorMessage = err.getDefaultMessage();
				error.put(fieldName, errorMessage);
			});
			return error;
			}
		
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(ConstraintViolationException.class)
	    public Map<String, String> handleValidationExceptions2(
	    		ConstraintViolationException ex) {
	        Map<String, String> error = new HashMap<>();
	        ex.getConstraintViolations().forEach((err) -> {
	            String fieldName = err.getPropertyPath().toString();
	            String errorMessage = err.getMessage();
	            error.put(fieldName, errorMessage);
	        });
	        return error;
	    }
}		

