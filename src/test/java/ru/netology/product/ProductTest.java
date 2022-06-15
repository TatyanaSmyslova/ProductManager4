package ru.netology.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product = new Product(2, "Название книги1", 1000);

    @Test
    void shouldNotMatchesProduct() {
        boolean actual = product.matches("");
        assertEquals(false, actual);
    }

    @Test
    void shouldMatchesProduct() {
        boolean actual = product.matches("Название книги1");
        assertEquals(true, actual);
    }
}