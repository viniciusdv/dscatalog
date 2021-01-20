package com.dscatalog.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dscatalog.dscatalog.entities.Category;
import com.dscatalog.dscatalog.repositories.CategoryRepository;

@Service // Registra a classe como mecanismo de injeção de dependencia automatico

public class CategoryService {
	
	@Autowired // Cria Dependencia
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		

   return repository.findAll();

	

}
	
}	
