package com.Services.UtilsServices.services.impl;
import com.Services.UtilsServices.repository.CategoriaRepository;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

import com.Services.UtilsServices.dto.ProductoDTO;
import com.Services.UtilsServices.dto.ProductoResponse;
import com.Services.UtilsServices.models.Categoria;
import com.Services.UtilsServices.models.Producto;
import com.Services.UtilsServices.repository.ProductosRepository;
import com.Services.UtilsServices.services.IProductosServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;


@Service
public class ProductosServices implements IProductosServices {

    private final Cloudinary cloudinary;
    private final ProductosRepository productosRepository;
    private final CategoriaRepository CategoriaRepository;



    public ProductosServices(ProductosRepository productosRepository,
                             @Value("${cloudinary.cloud-name}") String cloudName,
                             @Value("${cloudinary.api-key}") String apiKey,
                             @Value("${cloudinary.api-secret}") String apiSecret, CategoriaRepository categoriaRepository) {
        this.productosRepository = productosRepository;
        this.CategoriaRepository = categoriaRepository;
        this.cloudinary = new Cloudinary(Map.of(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    @Override
    public ResponseEntity<ProductoResponse> listarProductos() {
        // 1. Obtener la lista de Producto desde el repositorio
        List<Producto> productos = productosRepository.findAll();
        List<ProductoDTO> productosDTO = new ArrayList<>();
        // 2. Transformar la lista de Producto a ProductoDTO
        for (Producto producto : productos) {  // Recorre cada producto
            ProductoDTO dto = mapToProductoDTO(producto);  // Convierte Producto → ProductoDTO
            productosDTO.add(dto);  // Agrega el DTO a la nueva lista
        }
        // 3. Crear metadatos
        ProductoResponse.Metadata metadata = this.buildeMetadata(productosDTO.size());
        // 4. Crear la respuesta
        ProductoResponse response = this.builderResponsive(productosDTO, metadata );
        // 5. Devolver la respuesta
        return ResponseEntity.ok(response);
    }

    private ProductoDTO mapToProductoDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio().doubleValue());
        dto.setImagen(producto.getImagenBase64());
        // 🔥 Obtener la categoría completa (necesitas un servicio/repositorio)
        if (producto.getCategoria() != null) {
            Optional<Categoria> categoria = CategoriaRepository.findById(producto.getCategoria());
            if (categoria.isPresent()) {
                dto.setCategoria(categoria.get());
            }
        }
        return dto;
    }


    @Override
    public  void persistirProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            if (producto.getImagenBase64() != null && !producto.getImagenBase64().isEmpty()) {
                String base64Image = producto.getImagenBase64().split(",")[1];
                String imageUrl = uploadImagen(base64Image);
                String imagenBase64 =  Base64.getEncoder().encodeToString(imageUrl.getBytes());
                producto.setImagenBase64(imagenBase64);
            }
        }
        productosRepository.saveAll(productos);

    }

    private ProductoResponse builderResponsive(List<ProductoDTO> productosDTO, ProductoResponse.Metadata metadata) {
        ProductoResponse response = new ProductoResponse();
        ProductoResponse.Content content = new ProductoResponse.Content();
        content.setProductos(productosDTO);
        response.setContent(content);
        response.setMetadata(metadata);
        return response;

    }

    private ProductoResponse.Metadata buildeMetadata(int size) {
        ProductoResponse.Metadata metadata = new ProductoResponse.Metadata();
        metadata.setTotalElementos(size);
        metadata.setPaginaActual(1);
        metadata.setTotalPaginas(1);
        metadata.setVersion("1.0.0");
        metadata.setFechaActualizacion("2023-10-25T12:00:00Z");

      return  metadata;
    }
    /*
    private ProductoDTO mapToProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio().doubleValue());
        if (producto.getCategoriaId() != null) {
            Optional<Categoria> categoria = CategoriaRepository.findById(producto.getCategoriaId());
            if (categoria.isPresent()) {
                productoDTO.setCategoria(categoria.get());
            }
        }
        return productoDTO;
    }*/

    public String uploadImagen(String fileBase64) {
        String base64Data = fileBase64.contains(",")
                ? fileBase64.split(",")[1]
                : fileBase64;
        Map<?, ?> uploadResult = null;
        try {
            uploadResult = cloudinary.uploader()
                    .upload("data:image/png;base64," + base64Data, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String secureUrl = (String) uploadResult.get("secure_url");
        return secureUrl;
    }

}




