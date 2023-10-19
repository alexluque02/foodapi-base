package com.salesianostriana.dam.foodapi.repos;

import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {

    @Query("SELECT DISTINCT p FROM Pedido p JOIN p.lineasPedido lp WHERE lp.producto = :producto")
    List<Pedido> pedidosConProductoDeterminado(@Param("producto") Producto producto);
}
