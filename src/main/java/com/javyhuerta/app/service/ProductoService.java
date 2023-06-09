package com.javyhuerta.app.service;

import com.javyhuerta.app.entity.Producto;
import com.javyhuerta.app.model.ProductoModel;
import com.javyhuerta.app.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {


    private final ProductoRepository repository;

    public List<ProductoModel> obtenerTodos() {
        return repository.findAll().stream()
                .map(producto -> {
                    ProductoModel productoModel = new ProductoModel();
                    productoModel.setNombre(producto.getNombre());
                    productoModel.setPrecio(producto.getPrecio());
                    productoModel.setId(Math.toIntExact(producto.getId()));
                    return  productoModel;
                })
                .collect(Collectors.toList());
    }

    public Producto obtenerUno(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Producto crear(ProductoModel producto) {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(producto.getNombre());
        nuevoProducto.setPrecio(producto.getPrecio());
        return repository.save(nuevoProducto);
    }

    public Producto actualizar(Producto producto, Long id) {

        Producto productoDb = this.obtenerUno(id);

        if (productoDb != null) {
            productoDb.setId(id);
            return repository.save(productoDb);
        }

        return null;
    }

    public Producto eliminar(Long id) {

        Producto productoDb = this.obtenerUno(id);

        if (productoDb != null) {
            this.eliminar(id);
            return productoDb;
        }

        return null;
    }
}
