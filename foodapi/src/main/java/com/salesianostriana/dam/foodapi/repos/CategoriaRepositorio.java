package com.salesianostriana.dam.foodapi.repos;

import com.salesianostriana.dam.foodapi.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}
