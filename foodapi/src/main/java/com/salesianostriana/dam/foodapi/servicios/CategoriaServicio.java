package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.CategoriaDto;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.repos.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServicio {

    private final CategoriaRepositorio repositorio;

    public Categoria add(CategoriaDto nueva){
        Categoria c= new Categoria();

        if(nueva != null) {//Preguntar si poniendo el id tiene que dar fallo o no
            c.setNombre(nueva.nombre());
        }

        return repositorio.save(c);
    }


    public List<Categoria> findAll(){
        return repositorio.findAll();
    }


}
