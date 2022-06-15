package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.product.Book;
import ru.netology.product.NotFoundException;
import ru.netology.product.Product;
import ru.netology.product.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private int nonexistentID = 5;
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book firstBook = new Book(1, "Название книги1", 1000, "Фамилия автора1");
    private Book secondBook = new Book(2, "Название книги2", 2000, "Фамилия автора1");
    private Smartphone firstSmartphone = new Smartphone(3, "Наименование смартфона1", 10_000, "Наименование производителя1");
    private Smartphone secondSmartphone = new Smartphone(4, "Наименование смартфона2", 20_000, "Наименование производителя1");

    @BeforeEach
    void setUp() {
        manager.add(firstBook);
        manager.add(secondBook);
        manager.add(firstSmartphone);
        manager.add(secondSmartphone);
    }

    @Test
    public void shouldGetAll() {
        Product[] expected = new Product[]{firstBook, secondBook, firstSmartphone, secondSmartphone};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralByBookAuthor() {
        Product[] expected = new Product[]{firstBook, secondBook};
        Product[] actual = manager.searchBy("Фамилия автора1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByBookTitle() {
        Product[] expected = new Product[]{firstBook};
        Product[] actual = manager.searchBy("Название книги1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralBySmartphoneManufacturer() {
        Product[] expected = new Product[]{firstSmartphone, secondSmartphone};
        Product[] actual = manager.searchBy("Наименование производителя1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindBySmartphoneTitle() {
        Product[] expected = new Product[]{firstSmartphone};
        Product[] actual = manager.searchBy("Наименование смартфона1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByString() {
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Фамилия автора2");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByID() {
        Product[] expected = new Product[]{secondBook};
        Product[] actual = manager.findByID(2);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByNonexistentID() {
        assertThrows(NotFoundException.class, () -> manager.findByID(nonexistentID));
    }

    @Test
    public void shouldRemoveByID() {
        manager.removeByID(4);
        Product[] expected = new Product[]{firstBook, secondBook, firstSmartphone};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotRemoveByNonexistentID() {
        manager.removeByID(nonexistentID);
        Product[] expected = new Product[]{firstBook, secondBook, firstSmartphone, secondSmartphone};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }
}