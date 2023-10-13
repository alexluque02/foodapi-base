package com.salesianostriana.dam.foodapi.controller;

import com.salesianostriana.dam.foodapi.dto.EditCategoriaDto;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.servicios.CategoriaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaServicio servicio;

    @PostMapping("/")
    public ResponseEntity<Categoria> addCategoria(@RequestBody EditCategoriaDto nuevo){

        Categoria c = servicio.addCategoria(nuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(c);

    }

}
