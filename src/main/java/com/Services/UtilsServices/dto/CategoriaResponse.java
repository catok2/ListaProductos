package com.Services.UtilsServices.dto;

import lombok.Data;

import java.util.List;

public class CategoriaResponse {

    private CategoriaResponse.Content content;
    private CategoriaResponse.Metadata metadata;

    @Data
    public static class Content {
        private List<CategoriaDTO> categorias;
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
