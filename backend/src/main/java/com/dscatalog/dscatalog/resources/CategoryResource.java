package com.dscatalog.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dscatalog.dscatalog.entities.Category;

@RestController // Torna a classe em um controlador REST
@RequestMapping(value =  "/categories") // Cria a rota de Acesso via API

public class CategoryResource {
	
     @GetMapping // Cria metodo GET para retorno da requsição
	public ResponseEntity<List<Category>> findAll(){
		
	 List<Category> list = new ArrayList<>();
	 list.add(new Category(1L,"Books"));
	 list.add(new Category(2L, "Eletronics")); // Cria uma lista de retorno 
	 return ResponseEntity.ok().body(list); // Retorna a lista via dados JSON
		
	}
	

}
