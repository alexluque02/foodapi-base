package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.pedido.AddPedidoDto;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.repos.ClienteRepositorio;
import com.salesianostriana.dam.foodapi.repos.PedidoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServicio {

    private final PedidoRepositorio repositorio;
    private final ClienteRepositorio clienteRepositorio
    public Pedido add (AddPedidoDto nuevo){
        Pedido p = new Pedido();
        if(nuevo!=null){
            Optional<Cliente> cliente = clienteRepositorio.findById(nuevo.idCliente());
            if(cliente.isPresent()){
                p.setCliente(cliente.get());
                p.setLineasPedido(nuevo.lineasPedido());
            }
        }
        return repositorio.save(p);
    }

}
