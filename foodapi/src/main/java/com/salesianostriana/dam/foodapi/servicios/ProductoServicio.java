package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.repos.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final ProductoRepositorio repositorio;

}
