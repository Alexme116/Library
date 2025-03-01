package Model;
public class Book {
    public String title;
    public String author;
    public int numberOfCopies;
    public String isbn;
    private boolean isAvailable;
    private int borrowedBy;

    public Book(String title, String author, int numberOfCopies, String isbn) {
        this.title = title;
        this.author = author;
        this.numberOfCopies = numberOfCopies;
        this.isbn = isbn;
        this.isAvailable = true;
        this.borrowedBy = -1;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Number of copies: " + numberOfCopies);
        System.out.println("ISBN: " + isbn);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
        System.out.println("----------------------------");
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public int getBorrowedBy() {
        return borrowedBy;
    }

    public void borrowBook(int patronId) {
        isAvailable = false;
        borrowedBy = patronId;
    }

    public void returnBook() {
        isAvailable = true;
        borrowedBy = -1;
    }
}
