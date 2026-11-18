package com.todocodeacademy.ferreteria.service;

import com.todocodeacademy.ferreteria.model.Producto;

import java.util.List;

public interface IProductoService {

    //metodos para el CRUD

    //READ
    List<Producto>listaProductos();
    Producto buscarProducto(Long id);

    //CREATE
    Producto crearProducto(Producto producto);

    //UPDATE
    Producto editarProducto(Long id, Producto nuevoProducto);

    //DELETE
    boolean deleteProducto (Long id);

}
