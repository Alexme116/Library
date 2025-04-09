package Model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;
    private final List<Patron> patrons;

    public Library() {
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    public synchronized void displayAllBooks(){
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

    public synchronized void displayPatron(){
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

    public synchronized void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public synchronized void removeBook(String ISBN) {
        boolean wasDeleted = books.removeIf(book -> book.getIsbn().equals(ISBN));
        if (wasDeleted) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public synchronized void addPatron(Patron patron) {
        if(patron.id < 0 ){
            System.out.println("ID cannot be negative.");
            return;
        }
        for(Patron p : patrons){
            if(p.id == patron.id){
                throw new IllegalArgumentException("Patron with this ID already exists.");
            }
        }
        patrons.add(patron);
        System.out.println("Patron added successfully.");
    }

    public synchronized Book searchBook(int type, String query){
        for (Book book : books){
            switch (type) {
                case 1 -> {
                    if (book.getTitle().equals(query)) return book;
                }
                case 2 -> {
                    if (book.getAuthor().equals(query)) return book;
                }
                case 3 -> {
                    if (book.getIsbn().equals(query)) return book;
                }
            }
        }
        throw new IllegalArgumentException("No book found.");
    }

    public synchronized Patron searchPatron(int id){
        for (Patron patron : patrons){
            if(patron.id == id){
                return patron;
            }
        }
        throw new IllegalArgumentException("No patron found.");
    }

    public synchronized void borrowBook(int patronId, String isbn) {
        try {
            Patron patron = searchPatron(patronId);
            Book book = searchBook(3, isbn);
            if (book.borrowBook()) {
                patron.borrowBook(book);
            } else {
                System.out.println("The patron is " + patronId);
                System.out.println("Book not available. No more copies left.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void returnBook(int patronId, String isbn) {
        try {
            Patron patron = searchPatron(patronId);
            Book book = searchBook(3, isbn);
            patron.returnBook(book);
            book.returnBook();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized boolean isbnAlreadyExists(String isbn){
        for(Book book : books){
            if(book.getIsbn().equals(isbn)){
                return true;
            }
        }
        return false;
    }

    public synchronized void editBook(String isbn, String title, String author, int numberOfCopies){
        for(Book book : books){
            if(book.getIsbn().equals(isbn)){
                book.setTitle(title);
                book.setAuthor(author);
                book.setNumberOfCopies(numberOfCopies);
                System.out.println("Book edited successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public synchronized void editPatron(int id, String name, String details){
        Patron patronToEdit = searchPatron(id);
        patronToEdit.setName(name);
        patronToEdit.setDetails(details);
        System.out.println("Patron edited successfully.");
    }
}
