package com.dscatalog.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dscatalog.dscatalog.entities.Category;
import com.dscatalog.dscatalog.entities.Product;


@Repository // Torna o componente injetavel nas dependecias
public interface ProductRepository extends JpaRepository<Product, Long> {

}
