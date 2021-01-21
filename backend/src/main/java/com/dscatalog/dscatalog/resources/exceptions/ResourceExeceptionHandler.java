package com.dscatalog.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dscatalog.dscatalog.services.exeptions.DatabaseExcepetion;
import com.dscatalog.dscatalog.services.exeptions.ResourcesNotFoundException;

@ControllerAdvice // Para tratar Execessões
public class ResourceExeceptionHandler {
	
  @ExceptionHandler(ResourcesNotFoundException.class)  // Pra ele saber o tipo de requisição que vai tratar 	
  public ResponseEntity<StandardError> entityNotFound(ResourcesNotFoundException e, HttpServletRequest request){
	
	  StandardError err = new StandardError();
	  err.setTimestamp(Instant.now());
	  err.setStatus(HttpStatus.NOT_FOUND.value());
	  err.setError("Resource not found");
	  err.setMessage(e.getMessage());
	  err.setPath(request.getRequestURI());
	  
	  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
		
	}
  
  
  @ExceptionHandler(DatabaseExcepetion.class)  // Pra ele saber o tipo de requisição que vai tratar 	
  public ResponseEntity<StandardError> database(DatabaseExcepetion e, HttpServletRequest request){ // Tratamento exceção banco
	
	  StandardError err = new StandardError();
	  err.setTimestamp(Instant.now()); // Pega instante atual
	  err.setStatus(HttpStatus.BAD_REQUEST.value()); // Retorna um corpo BAD_REQUEST
	  err.setError("Database Exceptions");
	  err.setMessage(e.getMessage());
	  err.setPath(request.getRequestURI()); // Mostra caminho PATH;
	  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err); // Retorna o Status HTTP
		
		
	}

}


