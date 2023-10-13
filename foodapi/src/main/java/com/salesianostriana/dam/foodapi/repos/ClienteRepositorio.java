package com.salesianostriana.dam.foodapi.repos;

import com.salesianostriana.dam.foodapi.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}
