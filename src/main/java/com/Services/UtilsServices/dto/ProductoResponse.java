package com.Services.UtilsServices.dto;

import lombok.Data;
import org.hibernate.boot.Metadata;

import java.util.List;
@Data
public class ProductoResponse {

    private Content content;
    private Metadata metadata;

    @Data
    public static class Content {
        private List<ProductoDTO> productos;
    }

    @Data
    public static class Metadata {
        private int totalElementos;
        private int paginaActual;
        private int totalPaginas;
        private String version;
        private String fechaActualizacion;
    }
}
