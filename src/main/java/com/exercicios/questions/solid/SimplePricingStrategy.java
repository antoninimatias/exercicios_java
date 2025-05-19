package com.exercicios.questions.solid;

import java.util.List;

// Implementação básica de estratégia de preço (OCP)
public class SimplePricingStrategy implements PricingStrategy {

    @Override
    public double calculateTotal(List<Item> items) {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
}
