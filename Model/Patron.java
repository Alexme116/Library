package Model;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String name;
    public int id;
    private String details;
    private final List<Book> borrowedBooks;

    public Patron(String name, int id, String details) {
        this.name = name;
        this.id = id;
        this.details = details;
        this.borrowedBooks = new ArrayList<>();
    }

    public void displayPatron() {
        System.out.println("Name: " + name +
                "\nID: " + id +
                "\nDetails: " + details +
                "\nBorrowed Books: " + borrowedBooks.size());
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        int copies = book.getNumberOfCopies();
        book.setNumberOfCopies(copies-1);
        System.out.println(name + " borrowed: " + book.getTitle());
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            System.out.println(name + " returned: " + book.getTitle());
        } else {
            System.out.println(name + " did not borrow: " + book.getTitle());
        }
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
