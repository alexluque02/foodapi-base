package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.categoria.CategoriaDto;
import com.salesianostriana.dam.foodapi.dto.categoria.EditCategoriaDto;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.repos.CategoriaRepositorio;
import com.salesianostriana.dam.foodapi.repos.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServicio {

    private final CategoriaRepositorio repositorio;
    private final ProductoRepositorio repositorioProducto;

    public Categoria add(EditCategoriaDto nueva){
        Categoria c= new Categoria();

        if(nueva != null && nueva.nombre() != null) {
            //El nombre no puede estar vacío
            c.setNombre(nueva.nombre());
            return repositorio.save(c);
        }

        return null;
    }


    public List<Categoria> findAll(){
        return repositorio.findAll();
    }

    public Optional<Categoria> findById(Long id){
        return repositorio.findById(id);
    }

    public int countProductosByCategoria(Categoria c){
        return repositorioProducto.countProductosByCategoria(c);
    }

    public Categoria edit(Long id, EditCategoriaDto editar){
        Optional<Categoria> categoriaOptional = findById(id);

        if (categoriaOptional.isPresent() && editar.nombre() != null) {
            Categoria categoria = categoriaOptional.get();
            categoria.setNombre(editar.nombre());
            return repositorio.save(categoria);
        }
        return null;
    }

    public Map<String, String> delete(Long id){
        Map<String, String> response = new HashMap<>();
        Categoria categoria = repositorio.findById(id).orElse(null);
        if (categoria != null && countProductosByCategoria(categoria) == 0) {
            repositorio.delete(categoria);
        } else if(categoria != null && countProductosByCategoria(categoria) > 0){
            response.put("error", "No se puede borrar una categoría que tiene productos asociados.");
        } else {
            response.put("not found", "No se ha encontrado la categoría");
        }
        return response;
    }


}
