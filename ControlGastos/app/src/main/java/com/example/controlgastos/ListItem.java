package com.example.controlgastos;

public class ListItem extends ListNode {

    private double precio;

    public ListItem(String name, double precio) {
        super(name);
        this.precio = precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    double getPrecio() {
        return precio;
    }
}
