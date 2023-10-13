package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.repos.PedidoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoServicio {

    private final PedidoRepositorio repositorio;

}
