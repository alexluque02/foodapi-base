package com.salesianostriana.dam.foodapi.modelo;

public class ClienteView {

    public static class ClienteBasic{}

    public static class ClienteSinPin extends ClienteBasic{}

    public static class ClienteList extends ClienteSinPin{}

    public static class ClienteDetails extends ClienteList{}

    public static class ClienteComplete{}

}
