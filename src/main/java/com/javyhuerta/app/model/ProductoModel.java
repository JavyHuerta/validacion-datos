package com.javyhuerta.app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductoModel {

    @Schema(example = "4", description = "Identificador unico autoincremental")
    private long id;

    @Schema(example = "Jabon", description = "Nombre del producto")
    private String nombre;

    @Schema(example = "485.63", description = "Precio del producto")
    private float precio;

    @Schema(example = "Limpieza", description = "Categoria del producto")
    private String categoria;
}
