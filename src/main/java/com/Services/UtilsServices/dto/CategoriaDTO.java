package com.Services.UtilsServices.dto;

import com.Services.UtilsServices.models.Producto;
import lombok.Data;

import java.util.List;

@Data
public class CategoriaDTO {
    private Integer id;
    private String nombre;
    private List<Producto> Productos;
}
