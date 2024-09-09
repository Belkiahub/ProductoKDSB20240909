package com.example.repositorio;

import com.example.modelo.ProductoKDSB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductKDSBRepository extends JpaRepository<ProductoKDSB, Integer> {
}
