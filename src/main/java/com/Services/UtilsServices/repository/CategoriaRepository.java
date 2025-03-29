package com.Services.UtilsServices.repository;

import com.Services.UtilsServices.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria , Integer> {
    /*@Query("SELECT DISTINCT c FROM Categoria c LEFT JOIN FETCH c.productos")
    List<Categoria> findAllCategoriasWithProductos();*/
    /*
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.productos WHERE c.id = :id")
    Categoria findByIdWithProductos(@Param("id") Integer id);*/
}
