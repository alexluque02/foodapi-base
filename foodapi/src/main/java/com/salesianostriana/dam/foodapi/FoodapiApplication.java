package com.salesianostriana.dam.foodapi;

import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.repos.PedidoRepositorio;
import com.salesianostriana.dam.foodapi.repos.ProductoRepositorio;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(description = "Una API para gestionar los pedidos de comida de un restaurante",
		version = "1.0",
		contact = @Contact(email = "alexanderluquehoffrogge@gmail.com", name = "Alexander"),
		license = @License(name = "FoodApi"),
		title = "API de Comida"
)
)
public class FoodapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodapiApplication.class, args);
	}





}
