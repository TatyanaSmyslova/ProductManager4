package ru.netology.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartphoneTest {
    private Book book = new Book(1, "Название книги1", 1000, "Фамилия автора1");
    private Smartphone smartphone = new Smartphone(3, "Название смартфона1", 10_000, "Наименование производителя1");

    @Test
    void shouldNotMatchesSearch() {
        boolean actual = smartphone.matches("");
        assertEquals(false, actual);
    }

    @Test
    void shouldMatchesSmartphoneByTitle() {
        boolean actual = smartphone.matches("Название смартфона1");
        assertEquals(true, actual);
    }

    @Test
    void shouldMatchesSmartphoneByManufacturer() {
        boolean actual = smartphone.matches("Наименование производителя1");
        assertEquals(true, actual);
    }
}