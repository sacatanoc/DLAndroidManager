package com.example.inventario;

import java.util.ArrayList;

public class Producto {
    private final int codigo;
    private static int contador = 1000;
    public static ArrayList<Producto> inventario = new ArrayList<>();
    public String nombre;
    public double precio;
    public double precioC;
    public int cantidad;
    public String categoria;
    public Producto(String nombre, String categoria, double precio,double precioC, int cantidad) {
        this.codigo = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.precioC = precioC;
        this.cantidad = cantidad;
        this.categoria = categoria;
        inventario.add(this);
    }
    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.codigo = contador++;
        this.precio = 0.0;
        this.precioC = 0.0;
        this.categoria = "";
        inventario.add(this);
    }
    public Producto(String nombre, String categoria, double precio, double precioC){
        this.codigo = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.precioC = precioC;
        this.cantidad = 0;
        this.categoria = categoria;
        inventario.add(this);
    }

    public int getCodigo(){
        return this.codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

}
