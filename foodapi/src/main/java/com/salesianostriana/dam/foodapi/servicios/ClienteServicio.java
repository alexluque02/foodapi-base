package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.repos.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServicio {

    private final ClienteRepositorio repositorio;

}
