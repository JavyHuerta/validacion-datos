package com.javyhuerta.app.controller;

import com.javyhuerta.app.entity.Producto;
import com.javyhuerta.app.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductosController {

    private ProductoService service;

    @GetMapping
    public List<Producto> obternerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Producto obternerUno(@PathVariable Long id) {
        return service.obtenerUno(id);
    }

    @PostMapping
    public Producto nuevoProducto(@RequestBody Producto producto) {
        return service.crear(producto);
    }

    @PutMapping("/producto/{id}")
    public Producto editarProducto(@RequestBody Producto producto, @PathVariable Long id) {
        return service.actualizar(producto,id);
    }

    @DeleteMapping("/producto/{id}")
    public Producto editarProducto(@PathVariable Long id) {
        return service.eliminar(id);
    }

}
