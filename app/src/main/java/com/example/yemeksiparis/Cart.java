package com.example.yemeksiparis;

import java.util.ArrayList;

public class Cart {
    private ArrayList<MenuItem> cartlist;

    public Cart() {
        this.cartlist = new ArrayList<>();
    }

    public void addToCart(MenuItem mi) {
        this.cartlist.add(mi);
    }

    public void clearCart() {
        cartlist.clear();
    }

    public double calculateTotal() {
        double total = 0.0;
        for (int i = 0; i < cartlist.size(); i++) {
            total = total + cartlist.get(i).getPrice();
        }
        return total;
    }
}
