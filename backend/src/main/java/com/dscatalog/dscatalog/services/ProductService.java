package com.dscatalog.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dscatalog.dscatalog.dto.ProductDTO;
import com.dscatalog.dscatalog.entities.Product;
import com.dscatalog.dscatalog.repositories.ProductRepository;
import com.dscatalog.dscatalog.services.exeptions.DatabaseExcepetion;
import com.dscatalog.dscatalog.services.exeptions.ResourcesNotFoundException;


@Service // Registra a classe como mecanismo de injeção de dependencia automatico
public class ProductService {
	
	@Autowired // Cria Dependencia
	private ProductRepository repository;
     
	@Transactional(readOnly =  true) // Cria uma transação e melhora performace de dados
	
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest){ // Cria metodo do FindAll passando o DTO do Product
	Page<Product> list = repository.findAll(pageRequest);
	return list.map(x -> new ProductDTO(x)); // Coverte a List Product em ProductDTO
   
   //Retorna FindAll


}
	@Transactional(readOnly =  true) // Cria uma transação e melhora performace de dados
	public ProductDTO finById(Long id) {
	
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(()-> new ResourcesNotFoundException("Erro ao consultar Dados !"));
		return new ProductDTO(entity , entity.getCategories());
	}
	
	@Transactional
	public ProductDTO insert (ProductDTO dto) {
		
		Product entity = new Product();
		//entity.setName(dto.getName());
        entity =  repository.save(entity);
        return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		
        
		
       try{
			
			
		
		Product entity =  repository.getOne(id); // Usar get One para atualizar algum dado
		//entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProductDTO(entity);
		
		
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
