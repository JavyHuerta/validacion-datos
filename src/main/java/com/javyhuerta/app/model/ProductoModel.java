package com.javyhuerta.app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoModel {

    @Schema(example = "4", description = "Identificador unico autoincremental", hidden = true)
    private long id;

    @NotNull(message = "El nombre es requerido")
    @Size(min = 5, max = 20)
    @Schema(example = "Jabon", description = "Nombre del producto")
    private String nombre;

    @NotNull(message = "El precio es requerido")
    @Min(value = 5)
    @Schema(example = "485.63", description = "Precio del producto")
    private float precio;

    @NotNull(message = "La fecha es requerida")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(example = "2025-11-26", description = "Fecha de caducidad producto")
    private LocalDate fechaCaducidad;

    @Valid
    @Schema(implementation = CategoriaModel.class, description = "Categoria del producto")
    private CategoriaModel categoria;





}
