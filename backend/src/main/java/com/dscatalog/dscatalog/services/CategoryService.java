package com.dscatalog.dscatalog.services;

import java.util.List;


import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dscatalog.dscatalog.dto.CategoryDTO;
import com.dscatalog.dscatalog.entities.Category;
import com.dscatalog.dscatalog.repositories.CategoryRepository;
import com.dscatalog.dscatalog.services.exeptions.DatabaseExcepetion;
import com.dscatalog.dscatalog.services.exeptions.ResourcesNotFoundException;

import javax.persistence.EntityNotFoundException;


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
	@Transactional(readOnly =  true) // Cria uma transação e melhora performace de dados
	public CategoryDTO finById(Long id) {
	
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(()-> new ResourcesNotFoundException("Erro ao consultar Dados !"));
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO insert (CategoryDTO dto) {
		
		Category entity = new Category();
		entity.setName(dto.getName());
        entity =  repository.save(entity);
        return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		
        
		
       try{
			
			
		
		Category entity =  repository.getOne(id); // Usar get One para atualizar algum dado
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
		
		
	}
	
	  catch(EntityNotFoundException e) {
		  
		 
	   throw new ResourcesNotFoundException("Id não Encontrado : " +id);
			
			
	}
       
	}
	
	public void delete(Long id) {
	
	try { 
		
		
	repository.deleteById(id); // Chama função  respository para deletar
		
	}
	catch(EmptyResultDataAccessException e) { // Exception de ID não encontrado
		
	
		throw new ResourcesNotFoundException("Id Não Encontrado  : " +id);
		
		
	}
	
	catch(DataIntegrityViolationException e) { // Exception para não deixar deltar uma categoria que tem produtos
		
		
		throw new DatabaseExcepetion("Integridade Violada");
		
	}

	}
}
