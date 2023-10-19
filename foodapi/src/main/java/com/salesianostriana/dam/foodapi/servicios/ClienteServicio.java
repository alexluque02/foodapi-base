package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.cliente.EditClienteDto;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.repos.ClienteRepositorio;
import com.salesianostriana.dam.foodapi.repos.PedidoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServicio {

    private final ClienteRepositorio repositorio;
    private final PedidoRepositorio pedidoRepositorio;

    public Cliente add(EditClienteDto nuevo){
        Cliente c = new Cliente();
        if(nuevo!=null && nuevo.nombre() != null && nuevo.email() != null
        && nuevo.telefono() != null && nuevo.pin() != 0){
            //Un cliente debería tener proporcionar obligatoriamente todos los datos
            c.setNombre(nuevo.nombre());
            c.setEmail(nuevo.email());
            c.setTelefono(nuevo.telefono());
            c.setPin(nuevo.pin());
            return repositorio.save(c);
        }
        return null;
    }

    public List<Cliente> findAll(){
        return repositorio.findAll();
    }

    public Optional<Cliente> findById(Long id){
        return repositorio.findById(id);
    }

    public Cliente edit(Long id, EditClienteDto editar){
        Optional<Cliente> cliente = findById(id);
        if(cliente.isPresent() && editar.nombre() != null && editar.email() != null
                && editar.telefono() != null && editar.pin() != 0){
            //Para editar debemos tener en cuenta todos los datos del cliente
            Cliente c = cliente.get();
            c.setNombre(editar.nombre());
            c.setEmail(editar.email());
            c.setPin(editar.pin());
            c.setTelefono(editar.telefono());
            return repositorio.save(c);
        }
        return null;
    }

    public Cliente delete(Long id){
        Optional <Cliente> cliente= repositorio.findById(id);
        if (cliente.isPresent()) {
            List<Pedido> pedidos = pedidoRepositorio.pedidosDeUnCliente(cliente.get());
            pedidos.forEach(pedidoRepositorio::delete);
            cliente.get().setPedidos(null);
            repositorio.delete(cliente.get());
            return cliente.get();
        }
        return null;
    }
}
