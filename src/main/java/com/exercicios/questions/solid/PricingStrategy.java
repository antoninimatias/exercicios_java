package com.exercicios.questions.solid;

import java.util.List;

// Interface para estratégias de cálculo (DIP)
public interface PricingStrategy {
    double calculateTotal(List<Item> items);
}
