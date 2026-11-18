package com.todocodeacademy.ferreteria.controller;

import com.todocodeacademy.ferreteria.model.Producto;
import com.todocodeacademy.ferreteria.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    private IProductoService prodServ;

    public ProductoRestController(IProductoService prodServ) {
        this.prodServ = prodServ;
    }
//BUSCAR PRODUCTOS
    @GetMapping
    public List<Producto>traerProductos(){
        return prodServ.listaProductos();

    }
//BUSCAR UN PRODUCTO
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProducto(@PathVariable Long id){
        Producto prod= prodServ.buscarProducto(id);

        if(prod==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("no se encuentra un producto con ese codigo");

        }
        return ResponseEntity.ok(prod);
    }

//CREATE
    @PostMapping
    public ResponseEntity<?>crearProducto(@RequestBody Producto prod){

        Producto productoCreado= prodServ.crearProducto(prod);

        if(productoCreado==null){
            return ResponseEntity.badRequest()
                    .body("los datos del producto no son validos");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoCreado);
    }

//UPDATE
    @PutMapping("/{id}")
public ResponseEntity<?>editarProducto(@PathVariable Long id,
                                       @RequestBody  Producto nuevoProd){
        Producto prodEditado = prodServ.editarProducto(id ,nuevoProd);

        if(prodEditado==null){
            return ResponseEntity.badRequest()
                    .body("no fue posible editar el producto");
        }
        return ResponseEntity.ok(prodEditado);

    }
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProducto(@PathVariable Long id){

        boolean eliminado= prodServ.deleteProducto(id);

        if (eliminado==false){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro un producto con el codigo " + id);

        }

        return  ResponseEntity.ok("el producto se ha eliminado correctamente");
    }
}
