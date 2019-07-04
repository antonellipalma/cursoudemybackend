package com.map.primeiroprojeto.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.map.primeiroprojeto.services.exception.DataIntegrityException;
import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;



@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErroPadraoAplicacao> objetoNaoEncontrado( ObjectNotFoundException e, HttpServletRequest request){
			ErroPadraoAplicacao err= new ErroPadraoAplicacao(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
		
	 
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ErroPadraoAplicacao> dataIntegrity( DataIntegrityException e, HttpServletRequest request){
			ErroPadraoAplicacao err= new ErroPadraoAplicacao(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroValidacao> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		//ErroValidacao err=new ErroValidacao(HttpStatus.BAD_REQUEST.value(), e.getMessage(),System.currentTimeMillis());
		ErroValidacao err=new ErroValidacao(HttpStatus.BAD_REQUEST.value(), "Erro de Validacao",System.currentTimeMillis());
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			err.addErroLista(x.getField(), x.getDefaultMessage());			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}
	
}
