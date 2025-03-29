package com.Services.UtilsServices.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private BigDecimal precio;
    private String imagenBase64;
    private String descripcion;

    private Integer estado;

    @Column(name = "categoria_id")
    private Integer categoria;  // El id de la categoría (no el objeto)
}
