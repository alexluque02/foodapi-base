package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.repos.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaServicio {

    private final CategoriaRepositorio repositorio;

}
