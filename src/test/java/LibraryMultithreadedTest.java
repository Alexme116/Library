import org.junit.jupiter.api.Test;
import Model.Library;
import Model.Patron;
import Model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryMultithreadedTest {

    @Test
    public void testConcurrentBorrowingAndReturning() throws InterruptedException {
        Library library = new Library();
        String bookTitle = "Multithreading 101";
        library.registerBook(bookTitle, "Author X", 3, "ISBN-12345");

        int patronsCount = 10;
        CountDownLatch latch = new CountDownLatch(patronsCount);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < patronsCount; i++) {
            final int id = i;
            Thread thread = new Thread(() -> {
                Patron patron = new Patron("Patron-" + id, id, "Details", library);
                patron.run();
                latch.countDown();
            });
            threads.add(thread);
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all to finish
        latch.await();

        Book book = library.getBook(bookTitle);
        assertNotNull(book);
        assertEquals(3, book.getNumberOfCopies(), "All copies should be returned after operations");

        System.out.println("Final copies of '" + bookTitle + "': " + book.getNumberOfCopies());
        library.logLibraryState();
    }
}
