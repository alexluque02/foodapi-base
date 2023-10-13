package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.EditCategoriaDto;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.repos.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServicio {

    private final CategoriaRepositorio repositorio;

    public Categoria addCategoria(EditCategoriaDto nueva){
        Categoria c= new Categoria();

        if(nueva != null && nueva.id()==null) {//Cambiar sobre el id
            c.setNombre(nueva.nombre());
        }

        return repositorio.save(c);
    }



}
