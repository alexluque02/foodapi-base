package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.ClienteDto;
import com.salesianostriana.dam.foodapi.dto.EditClienteDto;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.repos.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServicio {

    private final ClienteRepositorio repositorio;

    public Cliente add(EditClienteDto nuevo){
        Cliente c = new Cliente();
        if(c!=null){
            c.setNombre(nuevo.nombre());
            c.setEmail(nuevo.email());
            c.setTelefono(nuevo.telefono());
            c.setPin(nuevo.pin());
        }
        return repositorio.save(c);
    }

    public List<Cliente> findAll(){
        return repositorio.findAll();
    }

    public Optional<Cliente> findById(Long id){
        return repositorio.findById(id);
    }

    public Cliente edit(Long id, EditClienteDto editar){
        Optional<Cliente> cliente = findById(id);
        if(cliente.isPresent()){
            Cliente c = cliente.get();
            c.setNombre(editar.nombre());
            c.setEmail(editar.email());
            c.setPin(editar.pin());
            c.setTelefono(editar.telefono());
            return repositorio.save(c);
        }
        return null;
    }
}
