package com.salesianostriana.dam.foodapi;

import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.repos.PedidoRepositorio;
import com.salesianostriana.dam.foodapi.repos.ProductoRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FoodapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodapiApplication.class, args);
	}





}
