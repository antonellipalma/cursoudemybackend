package com.map.primeiroprojeto.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.map.primeiroprojeto.services.exception.ObjectNotFoundException;



@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErroPadraoAplicacao> ObjetoNaoEncontrado( ObjectNotFoundException e, HttpServletRequest request){
			ErroPadraoAplicacao err= new ErroPadraoAplicacao(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
			//return ResponseEntity.ok().body(err);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
		
}
