package com.todocodeacademy.ferreteria.repository;

import com.todocodeacademy.ferreteria.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long > {


}
