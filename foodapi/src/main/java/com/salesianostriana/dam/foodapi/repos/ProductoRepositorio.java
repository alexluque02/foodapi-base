package com.salesianostriana.dam.foodapi.repos;

import com.salesianostriana.dam.foodapi.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
