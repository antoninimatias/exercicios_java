package com.exercicios.questions.solid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Classe Order refatorada (SRP)
public class Order {

    private final List<Item> items;
    private final PricingStrategy pricingStrategy;

    // Injeção de dependência (DIP)
    public Order(PricingStrategy pricingStrategy) {
        this.items = new ArrayList<>();
        this.pricingStrategy = pricingStrategy;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double calculateTotal() {
        return pricingStrategy.calculateTotal(items);
    }
}
