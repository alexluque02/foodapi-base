package com.salesianostriana.dam.foodapi.servicios;

import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoListDto;
import com.salesianostriana.dam.foodapi.dto.pedido.AddPedidoDto;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.repos.ClienteRepositorio;
import com.salesianostriana.dam.foodapi.repos.PedidoRepositorio;
import com.salesianostriana.dam.foodapi.repos.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class PedidoServicio {

    private final PedidoRepositorio repositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ProductoRepositorio productoRepositorio;
    public Pedido add(AddPedidoDto nuevo) {
        Pedido p = new Pedido();

        if (nuevo != null && nuevo.lineasPedido() != null && nuevo.idCliente() != null) {
            Optional<Cliente> cliente = clienteRepositorio.findById(nuevo.idCliente());

            if (cliente.isPresent()) {
                p.setCliente(cliente.get());
                p.setFecha(LocalDateTime.now());

                for (LineaPedidoListDto linea : nuevo.lineasPedido()) {
                    Optional<Producto> productoOptional = productoRepositorio.findById(linea.idProducto());

                    if (productoOptional.isPresent()) {
                        LineaPedido lineaPedido = new LineaPedido();
                        lineaPedido.setProducto(productoOptional.get());
                        lineaPedido.setCantidad(linea.cantidad());
                        lineaPedido.setPrecioUnitario(lineaPedido.getProducto().getPrecio());
                        p.addLineaPedido(lineaPedido);
                    }else{
                        return null; //Para que no puedan añadirse productos inexistentes a las líneas de pedido
                    }
                }
                return repositorio.save(p);
            }
        }
        return null;
    }

    public List<Pedido> findAll(){
        return repositorio.findAll();
    }

    public Optional<Pedido> findById(Long id){
        return repositorio.findById(id);
    }


    public Pedido deleteLinea(Long id, Long codLinea){
        Optional<Pedido> pedido = findById(id);
        if(pedido.isPresent()){
            Pedido p = pedido.get();
            List<LineaPedido> lineas = pedido.get().getLineasPedido();
            if(!lineas.isEmpty()){
                Optional<LineaPedido> first = lineas.stream()
                        .filter(linea -> linea.getId().getPedido().equals(p) && linea.getId().getCodLinea().equals(codLinea))
                        .findFirst();
                if (first.isPresent()){
                    LineaPedido lineaAEliminar = first.get();
                    lineas.remove(lineaAEliminar);
                    return repositorio.save(p);
                }
            }
        }
        return null;
    }

    public Pedido modifyCant(Long id, Long codLinea, int cantidad){
        Optional<Pedido> pedido = findById(id);

        if(pedido.isPresent()){
            List<LineaPedido> lineas = pedido.get().getLineasPedido();
            if(!lineas.isEmpty() && cantidad > 0){
                Optional<LineaPedido> first = lineas.stream()
                        .filter(linea -> linea.getCodLinea().equals(codLinea))
                        .findFirst();
                if (first.isPresent()){
                    LineaPedido lineaAModificar = first.get();
                    lineaAModificar.setCantidad(cantidad);
                    return repositorio.save(pedido.get());
                }
            }
        }
        return null;
    }

    public Pedido modifyPedidoProdAndCant(Long id, Long idProd, int cant){
        Optional<Pedido> pedido = findById(id);

        if(pedido.isPresent()){
            Optional<Producto> producto = productoRepositorio.findById(idProd);
            if(producto.isPresent() && cant>0){
                LineaPedido lineaNueva = new LineaPedido();
                lineaNueva.setProducto(producto.get());
                lineaNueva.setPedido(pedido.get());
                lineaNueva.setCantidad(cant);
                pedido.get().addLineaPedido(lineaNueva);
                return repositorio.save(pedido.get());
            }
        }
        return null;
    }

    public Pedido delete(Long id){
        Optional<Pedido> pedido = repositorio.findById(id);
        if(pedido.isPresent()){
            repositorio.delete(pedido.get());
            return pedido.get();
        }

        return null;
    }
}
