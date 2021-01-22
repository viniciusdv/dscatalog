package com.dscatalog.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity // Define a classe como uma entidade 
@Table(name =  "tb_category") // Criae Define a tabela no banco de Dados
public class Category  implements Serializable{

	
private static final long serialVersionUID = 1L;

@Id // Define o ID no Banco
@GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o Auto_Increment no ID
 private Long id;
 private String name;
 
 @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //  Armazena horario sem time-zone padrão (UTC)
 private Instant createdAt;

 
 @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //  Armazena horario sem time-zone padrão (UTC)
 private Instant updateAt;

 public Category() {
	 
	
	 
 }
 
 

public Instant getCreatedAt() {
	return createdAt;
}



public Instant getUpdateAt() {
	return updateAt;
}


@PrePersist // Sempre que tiver um insert  ele seta a variavel com o instante atual da inserção
public  void prePersist() {
	
	createdAt = Instant.now();
	
	
}

@PreUpdate // Sempre que tiver um update ele define  a variavel Instant com  a hora da alteração
public void preUpdate() {
	
	updateAt = Instant.now();	
}

public Category(Long id, String name) {
	
	super();
	this.id = id;
	this.name = name;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Category other = (Category) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}
 
 
	
	
}
