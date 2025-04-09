package src.main.java.Model;

import java.util.*;
import java.util.logging.Logger;

public class Library {
    private final Map<String, Book> books = new HashMap<>();
    private static final Logger logger = Logger.getLogger(Library.class.getName());

    public synchronized void registerBook(String title, String author, int numberOfCopies, String isbn) {
        if (!books.containsKey(title)) {
            books.put(title, new Book(title, author, numberOfCopies, isbn));
            System.out.println("Book registered: " + title + " by " + author + " (ISBN: " + isbn + ")");
        } else {
            System.out.println("Book already exists: " + title);
        }
    }

    public synchronized boolean borrowBook(String title, String patronName) {
        Book book = books.get(title);
        if (book == null) {
            System.out.println(patronName + " tried to borrow a non-existent book: " + title);
            return false;
        }

        boolean borrowed = book.borrow();
        if (borrowed) {
            System.out.println(patronName + " borrowed: " + title);
        } else {
            System.out.println(patronName + " tried to borrow but no copies available: " + title);
        }
        return borrowed;
    }

    public synchronized void returnBook(String title, String patronName) {
        Book book = books.get(title);
        if (book != null) {
            book.returnBook();
            System.out.println(patronName + " returned: " + title);
        } else {
            System.out.println(patronName + " tried to return a book not found: " + title);
        }
    }

    public synchronized String getRandomBookTitle() {
        if (books.isEmpty()) return null;
        List<String> titles = new ArrayList<>(books.keySet());
        return titles.get(new Random().nextInt(titles.size()));
    }

    public Book getBook(String title) {
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(title)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void logLibraryState() {
        for (Map.Entry<String, Book> entry : books.entrySet()) {
            logger.info("Libro: " + entry.getValue().getTitle() + ", Copias disponibles: " + entry.getValue().getNumberOfCopies());
        }
    }
}
