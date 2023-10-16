package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.ClienteDto;
import com.salesianostriana.dam.foodapi.dto.EditClienteDto;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.repos.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
