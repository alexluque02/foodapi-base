package com.salesianostriana.dam.foodapi.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id @GeneratedValue
    private Long id;

    private String nombre;

    private String imagen;

    private String descripcion;

    private double precio;

    private double precioOferta;

    @ElementCollection
    private List<String> tags;

    @ManyToOne
    private Categoria categoria;

}
