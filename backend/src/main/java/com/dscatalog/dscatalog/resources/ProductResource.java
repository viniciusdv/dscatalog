package com.dscatalog.dscatalog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dscatalog.dscatalog.dto.ProductDTO;
import com.dscatalog.dscatalog.services.ProductService;

@RestController // Torna a classe em um controlador REST
@RequestMapping(value = "/products") // Cria a rota de Acesso via API

public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping // Cria metodo GET para retorno da Requsição

	public ResponseEntity<Page<ProductDTO>> findAll(
			
			
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
					
			
				
			) {
		
		    PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		    
		   
		Page<ProductDTO> list = service.findAllPaged(pageRequest); // Cria a lista do Product , buscando todos os registros
		return ResponseEntity.ok().body(list); // Retorna a lista em formato JSON , retornando a "lista"

	}

	@GetMapping(value = "/{id}") // Cria metodo GET para retorno da requsicação com o ID
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) { // Atribui Long ID como variavel da rota.
		ProductDTO dto = service.finById(id); // Cria a lista do Product , buscando todos os registros
		return ResponseEntity.ok().body(dto); // Retorna a lista em formato JSON , retornando a "DTO"

	}

	@PostMapping // Insercao de registro
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {

		dto = service.insert(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

		return ResponseEntity.created(uri).body(dto);

	}

	@PutMapping(value = "/{id}") // Atualização de registro
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {

		dto = service.update(id, dto);

		return ResponseEntity.ok().body(dto);

	}
	
	@DeleteMapping(value = "/{id}") //  Deletar Registro
	public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
	
	service.delete(id);

	return ResponseEntity.noContent().build(); // Retorna corpo vazio ao Json

	}

}
