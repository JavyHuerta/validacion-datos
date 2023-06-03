package com.javyhuerta.app.service;

import com.javyhuerta.app.entity.Producto;
import com.javyhuerta.app.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private  ProductoRepository repository;

    public List<Producto> obtenerTodos(){
        return  repository.findAll();
    }

    public Producto obtenerUno(Long id){
        return  repository.findById(id).orElse(null);
    }

    public Producto crear(Producto producto){
        return  repository.save(producto);
    }

    public Producto actualizar(Producto producto, Long id){

        Producto productoDb = this.obtenerUno(id);

        if (productoDb != null){
            productoDb.setId(id);
            return this.crear(productoDb);
        }

        return null;
    }
    public Producto eliminar(Long id){

        Producto productoDb = this.obtenerUno(id);

        if (productoDb != null){
            this.eliminar(id);
            return productoDb;
        }

        return null;
    }
}
