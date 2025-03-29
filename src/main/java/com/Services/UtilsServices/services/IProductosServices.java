package com.Services.UtilsServices.services;


import com.Services.UtilsServices.dto.ProductoDTO;
import com.Services.UtilsServices.dto.ProductoResponse;
import com.Services.UtilsServices.models.Producto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface IProductosServices {

    public ResponseEntity<ProductoResponse> listarProductos();

    public void persistirProductos(List<Producto> productos) throws IOException;


}
