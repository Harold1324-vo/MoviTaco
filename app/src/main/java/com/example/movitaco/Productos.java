package com.example.movitaco;

public class Productos {
    private Integer id;
    private String nombreProducto;
    private Double precioProducto;

    public Productos() {
    }

    public Productos(Integer id, String nombreProducto, Double precioProducto) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }
}
