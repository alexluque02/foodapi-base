package com.salesianostriana.dam.foodapi.repos;

import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    @Query("SELECT COUNT(p) FROM Producto p WHERE p.categoria = :categoria")
    int countProductosByCategoria(@Param("categoria")Categoria categoria);
}
