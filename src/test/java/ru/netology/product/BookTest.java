package ru.netology.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book = new Book(1, "Название книги1", 228, "Фамилия автора1");
    private Smartphone smartphone = new Smartphone(3, "Название смартфона1", 10_000, "Наименование производителя1");

    @Test
    void shouldNotMatchesSearch() {
        boolean actual = book.matches("");
        assertEquals(false, actual);
    }

    @Test
    void shouldMatchesBookByTitle() {
        boolean actual = book.matches("Название книги1");
        assertEquals(true, actual);
    }

    @Test
    void shouldMatchesBookByAuthor() {
        boolean actual = book.matches("Фамилия автора1");
        assertEquals(true, actual);
    }
}