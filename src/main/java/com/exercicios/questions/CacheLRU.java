package com.exercicios.questions;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLRU<K, V> {

    private final int capacidadeMaxima;
    private final Map<K, V> cache;

    public CacheLRU(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.cache = new LinkedHashMap<>(capacidadeMaxima, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > CacheLRU.this.capacidadeMaxima;
            }
        };
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public V get(K key) {
        return cache.getOrDefault(key, null);
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public int size() {
        return cache.size();
    }

    @Override
    public String toString() {
        return cache.toString();
    }

}

class Main {
    public static void main(String[] args) {
        CacheLRU<Integer, String> cache = new CacheLRU<>(3);

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        System.out.println(cache); // {1=A, 2=B, 3=C}

        cache.get(1); // Acessa chave 1
        cache.put(4, "D"); // Adiciona um novo elemento, removendo o menos usado (chave 2)

        System.out.println(cache); // {1=A, 3=C, 4=D}
    }
}

