package com.Services.UtilsServices.dto;


import com.Services.UtilsServices.models.Categoria;
import lombok.Data;

@Data
public class ProductoDTO {
        private Integer id;
        private String nombre;
        private Double precio;
        private String imagen;
        private Categoria categoria;
        private Integer estado;// Incluye la categoría completa


}
