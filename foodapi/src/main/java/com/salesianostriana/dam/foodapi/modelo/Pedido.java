package com.salesianostriana.dam.foodapi.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {

    @Id @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime fecha;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaPedido> lineasPedido = new ArrayList<>();

    // Helpers

    // En este caso el lado propietario es Pedido, ya que tiene CascadeType.ALL

    public void addLineaPedido(LineaPedido lineaPedido) {
        lineasPedido.add(lineaPedido);
        lineaPedido.setPedido(this);
    }

    public void removeLineaPedido(LineaPedido lineaPedido) {
        lineasPedido.remove(lineaPedido);
        lineaPedido.setPedido(null);
    }

}
