package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoDtoList;
import com.salesianostriana.dam.foodapi.dto.pedido.AddPedidoDto;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.repos.ClienteRepositorio;
import com.salesianostriana.dam.foodapi.repos.PedidoRepositorio;
import com.salesianostriana.dam.foodapi.repos.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServicio {

    private final PedidoRepositorio repositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ProductoRepositorio productoRepositorio;
    public Pedido add (AddPedidoDto nuevo){
        Pedido p = new Pedido();
        if(nuevo!=null){
            Optional<Cliente> cliente = clienteRepositorio.findById(nuevo.idCliente());
            if(cliente.isPresent()){
                p.setCliente(cliente.get());
                p.setFecha(LocalDateTime.now());
                for (LineaPedidoDtoList linea: nuevo.lineasPedido()) {
                    LineaPedido lineaPedido = new LineaPedido();
                    //No poner get()
                    lineaPedido.setProducto(productoRepositorio.findById(linea.idProducto()).get());
                    lineaPedido.setCantidad(linea.cantidad());
                    lineaPedido.setPrecioUnitario(lineaPedido.getProducto().getPrecio());
                    p.addLineaPedido(lineaPedido);
                }
            }
        }
        return repositorio.save(p);
    }

    public List<Pedido> findAll(){
        return repositorio.findAll();
    }

}
