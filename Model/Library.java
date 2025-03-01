package Model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Patron> patrons;

    public Library() {
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    public void displayAllBooks(){
        if (books.isEmpty()) {
            System.out.println("There are no books in the library.");
            return;
        }
        System.out.println("List of books:");
        for (int i = 0; i < books.size(); i++){
            System.out.println("----------------------------");
            System.out.println(i + 1 + ".");
            books.get(i).display();
        }
    }

    public void displayPatron(){
        if (patrons.isEmpty()) {
            System.out.println("There are no patrons in the library.");
            return;
        }
        System.out.println("List of patrons:");
        for (int i = 0; i < patrons.size(); i++){
            System.out.println("----------------------------");
            System.out.println(i + 1 + ".");
            patrons.get(i).displayPatron();
        }
    }

    public void addBook(String title, String author, int numberOfCopies, String isbn) {
        books.add(new Book(title, author, numberOfCopies, isbn));
        System.out.println("Book added successfully.");
    }

    public void removeBook(String ISBN) {
        boolean wasDeleted = books.removeIf(book -> book.isbn.equals(ISBN));
        if (wasDeleted) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not founded.");
        }
    }

    public void addPatron(String name, int id, String details) {
        patrons.add(new Patron(name, id, details ));
        System.out.println("Patron added successfully.");
    }

    private Book getBook(String query) {
        for (Book book : books) {
            if (book.title.contains(query) || book.author.contains(query) || book.isbn.contains(query)) {
                return book;
            }
        }
        return null;
    }

    public void searchBook(String query){
        Book book = getBook(query);
        if (book != null) {
            System.out.println("----------------------------");
            book.display();
        } else {
            System.out.println("Book not founded.");
        }
    }

    private Patron getPatron(int id) {
        for (Patron patron : patrons) {
            if (patron.id == id) {
                return patron;
            }
        }
        return null;
    }

    public void borrowBook(String query, int patronId) {
        Book book = getBook(query);
        Patron patron = getPatron(patronId);
        if (book != null) {
            if (patron != null) {
                if (book.getIsAvailable()) {
                    book.borrowBook(patronId);
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Book is not available.");
                }
            } else {
                System.out.println("Patron not founded.");
            }
        } else {
            System.out.println("Book not founded.");
        }
    }

    public void returnBook(String query, int patronId) {
        Book book = getBook(query);
        Patron patron = getPatron(patronId);
        if (book != null) {
            if (patron != null) {
                if (!book.getIsAvailable() && book.getBorrowedBy() == patronId) {
                    book.returnBook();
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book is not borrowed by this patron.");
                }
            } else {
                System.out.println("Patron not founded.");
            }
        } else {
            System.out.println("Book not founded.");
        }
    }
}