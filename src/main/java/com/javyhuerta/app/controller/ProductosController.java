package com.javyhuerta.app.controller;

import com.javyhuerta.app.entity.Producto;
import com.javyhuerta.app.model.ProductoModel;
import com.javyhuerta.app.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(
        name = "Productos",
        description = "Operaciones Rest con productos"
)
public class ProductosController {

    private final ProductoService service;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene los productos",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(
                                    schema = @Schema(implementation = ProductoModel.class)
                            ))
                    })
    })
    @Operation(summary = "Consulta todos los productos", description = "Obtiene todos los productos registrados")
    @GetMapping
    public ResponseEntity<List<ProductoModel>> obternerTodos() {
        List<ProductoModel> productos = service.obtenerTodos();

        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productos);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtiene un producto",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoModel.class)
                            )
                    })
    })
    @Operation(summary = "Consultar producto", description = "Consulta un producto por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obternerUno(@Parameter(name = "id", description = "Identificador unico del producto") @PathVariable Long id) {
        Producto producto = service.obtenerUno(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Crea un producto",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoModel.class)
                            )
                    })
    })
    @Operation(summary = "Crea un producto", description = "Registra un nuevo producto")
    @PostMapping
    public ResponseEntity<Producto> nuevoProducto(@RequestBody ProductoModel producto) {
        Producto productoDb = service.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoDb);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualiza el producto",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoModel.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "No se encontro el producto")
    })
    @Operation(summary = "Actualiza un productos", description = "Actualiza todo el producto")
    @PutMapping("/producto/{id}")
    public ResponseEntity<Producto> editarProducto(@RequestBody Producto producto, @Parameter(name = "id", description = "Identificador unico del producto") @PathVariable Long id) {
        Producto productoDb = service.actualizar(producto, id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se elimina el producto")
    })
    @Operation(summary = "Elimina un producto", description = "Elimina un producto")
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> editarProducto(@Parameter(name = "id", description = "Identificador unico del producto a eliminar") @PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
