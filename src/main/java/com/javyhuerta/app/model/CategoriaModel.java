package com.javyhuerta.app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaModel {

    @Schema(example = "4", description = "Identificador unico autoincremental", hidden = true)
    private Long id;

    @NotNull(message = "El nombre es requerido")
    private String nombre;

    @NotNull(message = "La descripcion es requerido")
    private String descripcion;

}
