package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.EditProductoDto;
import com.salesianostriana.dam.foodapi.dto.ProductoDto;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.repos.CategoriaRepositorio;
import com.salesianostriana.dam.foodapi.repos.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final ProductoRepositorio repositorio;
    private final CategoriaRepositorio categoriaRepositorio;

    public Producto add(EditProductoDto nuevo){
        Producto p = new Producto();
        if(nuevo!=null){
            p.setNombre(nuevo.nombre());
            p.setImagen(nuevo.imagen());
            p.setPrecio(nuevo.precio());
            p.setDescripcion(nuevo.descripcion());
            p.setPrecioOferta(nuevo.precioOferta());
            p.setTags(nuevo.tags());
            Long id=nuevo.categoria();
            if (categoriaRepositorio.findById(id).isPresent()) {
                p.setCategoria(categoriaRepositorio.findById(id).get());
            } else {
                p.setCategoria(null);
            }
        }
        return repositorio.save(p);
    }

    public List<Producto> findAll(){
        return repositorio.findAll();
    }

}
