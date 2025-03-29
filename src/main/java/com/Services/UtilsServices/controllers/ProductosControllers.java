package com.Services.UtilsServices.controllers;

import com.Services.UtilsServices.dto.ProductoDTO;
import com.Services.UtilsServices.dto.ProductoResponse;
import com.Services.UtilsServices.models.Producto;
import com.Services.UtilsServices.services.impl.ProductosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lista")
public class ProductosControllers {


    private final ProductosServices productoServices;

    public ProductosControllers(ProductosServices productoServices) {
        this.productoServices = productoServices;
    }

    @Autowired // Inyecta el repositorio en el constructor

    @GetMapping("/productos")
    public ResponseEntity<ProductoResponse> listarProductos() {
        return productoServices.listarProductos();
    }



    @PostMapping("/productos")
    @ResponseStatus(HttpStatus.CREATED) // Devuelve 201 sin cuerpo
    public void persistirProducto(@RequestBody List<Producto> productos) {
        productoServices.persistirProductos(productos);
    }


    /*
    @PostMapping("/subir-imagen")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        // Llamamos al servicio para subir la imagen y obtener la URL
        return productoServices.uploadImagen(file);
    }
    */
}
