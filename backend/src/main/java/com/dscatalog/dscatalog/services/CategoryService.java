package com.dscatalog.dscatalog.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dscatalog.dscatalog.dto.CategoryDTO;
import com.dscatalog.dscatalog.entities.Category;
import com.dscatalog.dscatalog.repositories.CategoryRepository;

@Service // Registra a classe como mecanismo de injeção de dependencia automatico

public class CategoryService {
	
	@Autowired // Cria Dependencia
	private CategoryRepository repository;
     
	@Transactional(readOnly =  true) // Cria uma transação e melhora performace de dados
	
	public List<CategoryDTO> findAll(){ // Cria metodo do FindAll passando o DTO do Category
	List<Category> list = repository.findAll();
	return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList()); // Coverte a List Category em CategoryDTO
   
   //Retorna FindAll


}
	
}	
