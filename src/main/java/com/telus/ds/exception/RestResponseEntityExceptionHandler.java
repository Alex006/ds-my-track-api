package com.telus.ds.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.telus.ds.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	//BadInputParamException
	@ExceptionHandler({ BadInputParamException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<Object> handleBadInputParamException(BadInputParamException ex) {
		log.error("BadInputParamException ", ex);

		Error error = new Error();
		error.setCode(Constants.ERROR_CODE_BAD_INPUT_PARAM);
		error.setMessage(ex.getMessage());

		log.error("handleBadInputParamException(): " + error.toString());
		return new ResponseEntity<>(error, createHeader(), HttpStatus.BAD_REQUEST);
	}
	
	//ResourceNotFoundException
	@ExceptionHandler({ ResourceNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<Object> handleHttpResourceNotFoundException(ResourceNotFoundException ex) {
		log.error("ResourceNotFoundException ", ex);

		Error error = new Error();
		error.setCode(Constants.ERROR_CODE_RESOURCE_NOT_FOUND);
		error.setMessage(ex.getMessage());

		log.error("handleHttpResourceNotFoundException(): " + error.toString());
		return new ResponseEntity<>(error, createHeader(), HttpStatus.NOT_FOUND);
	}
	
	//Exception
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	protected ResponseEntity<Object> handleExceptions(Exception e) {
		String message;
		Throwable cause;
		Throwable resultCause = e;
		while ((cause = resultCause.getCause()) != null && resultCause != cause) {
			resultCause = cause;
		}
		
		if (resultCause instanceof ConstraintViolationException) {
			message = (((ConstraintViolationException) resultCause).getConstraintViolations()).iterator().next().getMessage();
		} else {
			message = resultCause.getMessage();
		}

		Error error = new Error();
		error.setCode(Constants.ERROR_CODE_UNKNOWN_FAILURE);
		error.setMessage(resultCause.getClass().getName() + " : " + message);

		log.error("handleExceptions(): " + error.toString());
		return new ResponseEntity<>(error, createHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private HttpHeaders createHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		return headers;
	}

}
