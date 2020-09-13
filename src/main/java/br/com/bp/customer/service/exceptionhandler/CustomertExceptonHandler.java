package br.com.bp.customer.service.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.bp.customer.exception.CustomerNotFoundException;


@ControllerAdvice
public class CustomertExceptonHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		
		List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
		
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Error> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
		String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();
		
		List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({CustomerNotFoundException.class})
	public ResponseEntity<Object> handlerCustomerNotFound(CustomerNotFoundException ex, WebRequest request){
		String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();
		
		List<Error> erros = Arrays.asList(new Error(userMessage, developerMessage));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	private List<Error> criarListaDeErros(BindingResult bindingResult){
		List<Error> erros = new ArrayList<>();
		
		for (FieldError filedError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(filedError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = filedError.toString();
			erros.add(new Error(mensagemUsuario,mensagemDesenvolvedor));
		}
		return erros;
	}
	
	public static class Error{
		
		private String userMessage;
		private String developerMessage;
		
		public Error(String userMessage, String developerMessage) {
			this.userMessage = userMessage;
			this.developerMessage = developerMessage;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public void setUserMessage(String userMessage) {
			this.userMessage = userMessage;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}

		public void setDeveloperMessage(String developerMessage) {
			this.developerMessage = developerMessage;
		}
	}
}
