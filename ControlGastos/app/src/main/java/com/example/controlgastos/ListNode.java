package com.example.controlgastos;

public abstract class ListNode {

    private String name;

    public ListNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract double getPrecio();
}
