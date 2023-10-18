package com.salesianostriana.dam.foodapi.repos;

import com.salesianostriana.dam.foodapi.modelo.*;
import com.salesianostriana.dam.foodapi.servicios.ClienteServicio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class InitData {

    private final CategoriaRepositorio categoriaRepositorio;
    private final ProductoRepositorio productoRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final PedidoRepositorio pedidoRepositorio;

    @PostConstruct
    public void init(){
        Categoria c = new Categoria();
        c.setNombre("Veggie");

        Categoria c2 = new Categoria();
        c2.setNombre("Pasta");

        Categoria c3 = new Categoria();
        c3.setNombre("Burger");

        categoriaRepositorio.saveAll(List.of(c, c2, c3));

        Producto veggie1 = new Producto();
        veggie1.setNombre("Ensalada de Espinacas");
        veggie1.setImagen("ensalada.jpg");
        veggie1.setDescripcion("Ensalada fresca con espinacas, tomates y vinagreta.");
        veggie1.setPrecio(9.99);
        veggie1.setPrecioOferta(0.0);
        veggie1.setTags(List.of("ensalada", "veggie", "saludable"));
        veggie1.setCategoria(c);


        Producto veggie2 = new Producto();
        veggie2.setNombre("Burger Vegetariana");
        veggie2.setImagen("burger.jpg");
        veggie2.setDescripcion("Burger vegetariana con guarnición de papas fritas.");
        veggie2.setPrecio(12.99);
        veggie2.setPrecioOferta(0.0);
        veggie2.setTags(List.of("burger", "veggie", "papas fritas"));
        veggie2.setCategoria(c);


        Producto pasta1 = new Producto();
        pasta1.setNombre("Spaghetti Carbonara");
        pasta1.setImagen("spaghetti.jpg");
        pasta1.setDescripcion("Spaghetti con salsa carbonara y panceta.");
        pasta1.setPrecio(11.99);
        pasta1.setPrecioOferta(0.0);
        pasta1.setTags(List.of("pasta", "italiana", "carbonara"));
        pasta1.setCategoria(c2);


        Producto pasta2 = new Producto();
        pasta2.setNombre("Ravioli de Queso");
        pasta2.setImagen("ravioli.jpg");
        pasta2.setDescripcion("Ravioli relleno de queso y servido con salsa de tomate.");
        pasta2.setPrecio(10.99);
        pasta2.setPrecioOferta(0.0);
        pasta2.setTags(List.of("ravioli", "pasta", "queso"));
        pasta2.setCategoria(c2);


        Producto burger1 = new Producto();
        burger1.setNombre("Burger Clásica");
        burger1.setImagen("burger.jpg");
        burger1.setDescripcion("Nuestra burger clásica con carne a la parrilla.");
        burger1.setPrecio(8.99);
        burger1.setPrecioOferta(0.0);
        burger1.setTags(List.of("burger", "clásica", "parrilla"));
        burger1.setCategoria(c3);


        Producto burger2 = new Producto();
        burger2.setNombre("Burger BBQ");
        burger2.setImagen("bbq.jpg");
        burger2.setDescripcion("Burger con salsa BBQ, cebolla y bacon.");
        burger2.setPrecio(10.99);
        burger2.setPrecioOferta(0.0);
        burger2.setTags(List.of("burger", "BBQ", "bacon"));
        burger2.setCategoria(c3);



        productoRepositorio.saveAll(List.of(veggie1, veggie2, pasta1, pasta2, burger2, burger1));


        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan Pérez");
        cliente1.setEmail("juan@example.com");
        cliente1.setTelefono("123-456-7890");
        cliente1.setPin(1234);


        Cliente cliente2 = new Cliente();
        cliente2.setNombre("María López");
        cliente2.setEmail("maria@example.com");
        cliente2.setTelefono("987-654-3210");
        cliente2.setPin(5678);


        Cliente cliente3 = new Cliente();
        cliente3.setNombre("Pedro Gómez");
        cliente3.setEmail("pedro@example.com");
        cliente3.setTelefono("555-555-5555");
        cliente3.setPin(1111);


        clienteRepositorio.saveAll(List.of(cliente1, cliente2, cliente3));

        LineaPedido lp1 = new LineaPedido();
        lp1.setProducto(burger1);
        lp1.setCantidad(2);
        lp1.setPrecioUnitario(burger1.getPrecio());

        Pedido pedido1 = new Pedido();
        pedido1.setCliente(cliente1);
        pedido1.setFecha(LocalDateTime.now());
        pedido1.addLineaPedido(lp1);

        LineaPedido lp2 = new LineaPedido();
        lp2.setProducto(veggie1);
        lp2.setCantidad(1);
        lp2.setPrecioUnitario(veggie1.getPrecio());

        Pedido pedido2 = new Pedido();
        pedido2.setCliente(cliente2);
        pedido2.setFecha(LocalDateTime.now());
        pedido2.addLineaPedido(lp2);

        LineaPedido lp3 = new LineaPedido();
        lp3.setProducto(pasta2);
        lp3.setCantidad(3);
        lp3.setPrecioUnitario(pasta2.getPrecio());

        LineaPedido lp4 = new LineaPedido();
        lp4.setProducto(burger2);
        lp4.setCantidad(2);
        lp4.setPrecioUnitario(burger2.getPrecio());

        Pedido pedido3 = new Pedido();
        pedido3.setCliente(cliente3);
        pedido3.setFecha(LocalDateTime.now());
        pedido3.addLineaPedido(lp3);
        pedido3.addLineaPedido(lp4);

        pedidoRepositorio.saveAll(List.of(pedido1, pedido3, pedido2));
    }

}
