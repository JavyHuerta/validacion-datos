package com.javyhuerta.app.exception;

import com.javyhuerta.app.model.ProductoModel;
import lombok.Getter;

@Getter
public class ProductNameException extends  RuntimeException{

    private ProductoModel producto;

    public ProductNameException(ProductoModel producto){
        this.producto = producto;
    }
}
