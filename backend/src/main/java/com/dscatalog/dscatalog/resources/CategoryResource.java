package com.dscatalog.dscatalog.resources;

import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletSecurityElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

import com.dscatalog.dscatalog.dto.CategoryDTO;
import com.dscatalog.dscatalog.services.CategoryService;


@RestController // Torna a classe em um controlador REST
@RequestMapping(value =  "/categories") // Cria a rota de Acesso via API

public class CategoryResource {
	
	
	@Autowired  
	private CategoryService service;
	
     @GetMapping //  Cria metodo GET para retorno da Requsição
     
	 public ResponseEntity<List<CategoryDTO>>findAll(){
	 List <CategoryDTO> list = service.findAll(); // Cria a lista do Category , buscando todos os registros
	 return ResponseEntity.ok().body(list); // Retorna a lista em formato JSON , retornando a "lista"
	
     }
     
     
      @GetMapping(value= "/{id}") //  Cria metodo GET para retorno da requsicação com o ID
	 public ResponseEntity<CategoryDTO>findById(@PathVariable Long id){ //Atribui Long ID como variavel da rota.
	 CategoryDTO dto = service.finById(id); // Cria a lista do Category , buscando todos os registros
	 return ResponseEntity.ok().body(dto); // Retorna a lista em formato JSON , retornando a "DTO"
	
     }
      
      @PostMapping // Insercao de registro
      public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
    	  
    	  dto = service.insert(dto);
    	 
    	 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
    			  .buildAndExpand(dto.getId()).toUri();
    			    
    			    
    	  return ResponseEntity.created(uri).body(dto);
    			  
    	
    	  
      }
	
	

}
