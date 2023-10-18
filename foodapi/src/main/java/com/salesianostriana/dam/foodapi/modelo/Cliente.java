package com.salesianostriana.dam.foodapi.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    private String email;

    private String telefono;

    private int pin;

    @ToString.Exclude
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>();

}
