package src.test.java;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.main.java.Model.Library;
import src.main.java.Model.Patron;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

public class LibraryTest {

    private Library library;

    @BeforeEach
    public void setUp() {
        // Crear una nueva instancia de la biblioteca antes de cada prueba
        library = new Library();
    }

    @AfterEach
    public void tearDown() {
        // Borrar la instancia de la biblioteca después de cada prueba
        library = null;
    }

    @Test
    public void testBookBorrowAndReturnDecreasesAndIncreasesCopies() {
        library.registerBook("The Fault in Our Stars", "John Green", 3, "9780525478812");

        // Verificar que inicialmente haya 3 copias disponibles
        assertEquals(3, library.getBook("The Fault in Our Stars").getNumberOfCopies());

        Patron patron1 = new Patron("Patron-1", 1, "Student", library);
        Patron patron2 = new Patron("Patron-2", 2, "Teacher", library);
        Patron patron3 = new Patron("Patron-3", 3, "Guest", library);

        library.borrowBook("The Fault in Our Stars", "Patron-1");
        assertEquals(2, library.getBook("The Fault in Our Stars").getNumberOfCopies());
        library.borrowBook("The Fault in Our Stars", "Patron-2");
        assertEquals(1, library.getBook("The Fault in Our Stars").getNumberOfCopies());
        library.borrowBook("The Fault in Our Stars", "Patron-3");
        assertEquals(0, library.getBook("The Fault in Our Stars").getNumberOfCopies());

        library.returnBook("The Fault in Our Stars", "Patron-1");
        assertEquals(1, library.getBook("The Fault in Our Stars").getNumberOfCopies());
        library.returnBook("The Fault in Our Stars", "Patron-2");
        assertEquals(2, library.getBook("The Fault in Our Stars").getNumberOfCopies());
        library.returnBook("The Fault in Our Stars", "Patron-3");
        assertEquals(3, library.getBook("The Fault in Our Stars").getNumberOfCopies());
    }

    @Test
    public void testLoggingLibraryState() {
        library.registerBook("The Fault in Our Stars", "John Green", 3, "9780525478812");
        library.registerBook("Looking for Alaska", "John Green", 2, "9780525475064");

        Patron patron1 = new Patron("Patron-1", 1, "Student", library);
        Patron patron2 = new Patron("Patron-2", 2, "Teacher", library);
        Patron patron3 = new Patron("Patron-3", 2, "Guest", library);

        library.borrowBook("The Fault in Our Stars", "Patron-1");
        library.borrowBook("The Fault in Our Stars", "Patron-2");

        System.out.println("Estado de la biblioteca después de los préstamos:");
        library.logLibraryState();

        library.returnBook("The Fault in Our Stars", "Patron-1");
        library.returnBook("The Fault in Our Stars", "Patron-2");

        System.out.println("Estado de la biblioteca después de las devoluciones:");
        library.logLibraryState();
    }

}
