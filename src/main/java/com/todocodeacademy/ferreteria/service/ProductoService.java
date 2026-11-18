package com.todocodeacademy.ferreteria.service;

import com.todocodeacademy.ferreteria.model.Producto;
import com.todocodeacademy.ferreteria.repository.IProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    private IProductoRepository iproduRepo;

    public ProductoService(IProductoRepository iproduRepo) {
        this.iproduRepo = iproduRepo;
    }

    @Override
    public List<Producto> listaProductos() {
        return iproduRepo.findAll();
    }

    @Override
    public Producto buscarProducto(Long id) {
        return iproduRepo.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        //validacion de que el producto no sea null
        if (producto == null) {
            return null;
        }

        // validamos si cada uno de los elementos es null o no
        boolean valido = this.validarDatos(producto);

        if(valido == false){
            return null;
        }
        //el id se genera automaticamente en la BD y con esto la devolvemos junto con el producto
        return iproduRepo.save(producto);
    }

    @Override
    public Producto editarProducto(Long id, Producto nuevoProducto) {
        //buscar si existe el producto
        Producto prodExistente = buscarProducto(id);
        //validar
        if (prodExistente == null) {
            return null;
        }
        // validamos si cada uno de los elementos es null o no
        boolean valido = this.validarDatos(nuevoProducto);

        if(valido == false){
            return null;
        }

        //actualizamos los datos con el producto
        prodExistente.setCategoria(nuevoProducto.getCategoria());
        prodExistente.setDescripcion(nuevoProducto.getDescripcion());
        prodExistente.setNombre(nuevoProducto.getNombre());
        prodExistente.setPrecio(nuevoProducto.getPrecio());
        prodExistente.setStock(nuevoProducto.getStock());
        prodExistente.setMarca(nuevoProducto.getMarca());

        return iproduRepo.save(prodExistente);
    }

    @Override
    public boolean deleteProducto(Long id) {

        Producto prodExistente = buscarProducto(id);

        if (prodExistente == null) {
            return false;
        }
        iproduRepo.deleteById(id);
        return true;
    }
//metodo para validar cada uno de los atributos del objeto y permita modificar un solo atributo sin voler a poner todos.
    public boolean validarDatos(Producto prod) {

        if (prod.getNombre() == null || prod.getNombre().isBlank()) {
            return false;
        }
        if (prod.getMarca() == null || prod.getMarca().isBlank()) {
            return false;
        }
        if (prod.getDescripcion() == null || prod.getDescripcion().isBlank()) {
            return false;
        }

        if (prod.getCategoria() == null || prod.getCategoria().isBlank()) {
            return false;
        }

        if (prod.getPrecio() == null || prod.getPrecio()<=0) {
            return false;
        }

        if (prod.getStock()<=0 ) {
            return false;
        }

        return true;
    }

}
