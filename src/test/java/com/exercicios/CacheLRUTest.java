package com.exercicios;

import com.exercicios.questions.CacheLRU;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CacheLRUTest {

    @Test
    void testPutAndGet() {
        CacheLRU<Integer, String> cache = new CacheLRU<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        assertEquals("A", cache.get(1));
        assertEquals("B", cache.get(2));
        assertEquals("C", cache.get(3));
    }

    @Test
    void testEviction() {
        CacheLRU<Integer, String> cache = new CacheLRU<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.put(4, "D"); // Deve remover a chave 1 (LRU)

        assertNull(cache.get(1)); // Chave 1 foi removida
        assertEquals("B", cache.get(2));
        assertEquals("C", cache.get(3));
        assertEquals("D", cache.get(4));
    }

    @Test
    void testRemove() {
        CacheLRU<Integer, String> cache = new CacheLRU<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.remove(1);

        assertNull(cache.get(1)); // Chave removida
        assertEquals(1, cache.size()); // Apenas um item deve restar
    }

    @Test
    void testSize() {
        CacheLRU<Integer, String> cache = new CacheLRU<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        assertEquals(3, cache.size()); // Deve conter 3 elementos

        cache.put(4, "D"); // LRU (chave 1) será removida
        assertEquals(3, cache.size()); // Ainda deve conter 3 elementos
    }
}

