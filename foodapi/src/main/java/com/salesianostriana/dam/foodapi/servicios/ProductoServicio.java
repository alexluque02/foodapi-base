package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.CategoriaDto;
import com.salesianostriana.dam.foodapi.dto.EditProductoDto;
import com.salesianostriana.dam.foodapi.dto.ProductoDto;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.repos.CategoriaRepositorio;
import com.salesianostriana.dam.foodapi.repos.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final ProductoRepositorio repositorio;
    private final CategoriaRepositorio categoriaRepositorio;

    public Producto add(EditProductoDto nuevo) {
        Producto p = new Producto();
        if (nuevo != null) {
            p.setNombre(nuevo.nombre());
            p.setImagen(nuevo.imagen());
            p.setPrecio(nuevo.precio());
            p.setDescripcion(nuevo.descripcion());
            p.setPrecioOferta(nuevo.precioOferta());
            p.setTags(nuevo.tags());
            Long id = nuevo.categoria();
            if (categoriaRepositorio.findById(id).isPresent()) {
                p.setCategoria(categoriaRepositorio.findById(id).get());
            } else {
                p.setCategoria(null);
            }
        }
        return repositorio.save(p);
    }

    public Optional<Producto> findById(Long id) {
        return repositorio.findById(id);
    }

    public List<Producto> findAll() {
        return repositorio.findAll();
    }

    public Producto edit(Long id, EditProductoDto editar){
        Optional<Producto> productoOptional = findById(id);

            if (productoOptional.isPresent()) {
                Producto producto = productoOptional.get();
                producto.setNombre(editar.nombre());
                producto.setImagen(editar.imagen());
                producto.setDescripcion(editar.descripcion());
                producto.setPrecio(editar.precio());
                producto.setPrecioOferta(editar.precioOferta());
                producto.setTags(editar.tags());
                Long idCat = editar.categoria();
                if (idCat!=null && categoriaRepositorio.findById(idCat).isPresent()) {
                    producto.setCategoria(categoriaRepositorio.findById(idCat).get());
                } else {
                    producto.setCategoria(null);
                }
                return repositorio.save(producto);
            }
            return null;
    }

}
