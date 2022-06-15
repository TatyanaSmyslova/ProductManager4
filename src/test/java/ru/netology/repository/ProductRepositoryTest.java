package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.product.Book;
import ru.netology.product.NotFoundException;
import ru.netology.product.Product;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    int nonexistentID = 5;
    private ProductRepository repository = new ProductRepository();
    private Book firstBook = new Book(1, "Название книги1", 1000, "Фамилия автора1");
    private Book secondBook = new Book(2, "Название книги2", 2000, "Фамилия автора1");

    @BeforeEach
    void setUp() {
        repository.save(firstBook);
        repository.save(secondBook);
    }

    @Test
    public void shouldFindAll() {
        Product[] expected = new Product[]{firstBook, secondBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByID() {
        Product[] expected = new Product[]{secondBook};
        Product[] actual = repository.findByID(2);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByNonexistentID() {
        assertThrows(NotFoundException.class, () -> repository.findByID(nonexistentID));
    }

    @Test
    public void shouldRemoveByID() {
        repository.removeByID(2);
        Product[] expected = new Product[]{firstBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveByNonexistentID() {
        repository.removeByID(nonexistentID);
        Product[] expected = new Product[]{firstBook, secondBook};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}