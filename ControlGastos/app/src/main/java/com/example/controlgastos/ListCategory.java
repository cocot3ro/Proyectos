package com.example.controlgastos;

import java.util.LinkedList;
import java.util.List;

public class ListCategory extends ListNode {

    private List<ListNode> nodes;

    public ListCategory(String name, List<ListNode> nodes) {
        super(name);
        this.nodes = nodes;
    }

    public List<ListNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ListNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    double getPrecio() {
        double precio = 0;
        for (ListNode node : nodes) {
            precio += node.getPrecio();
        }
        return precio;
    }
}
