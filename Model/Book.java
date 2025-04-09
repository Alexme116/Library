package Model;

public class Book {
    private String title;
    private String author;
    private int numberOfCopies;
    private final String isbn;

    public Book(String title, String author, int numberOfCopies, String isbn) {
        this.title = title;
        this.author = author;
        this.numberOfCopies = numberOfCopies;
        this.isbn = isbn;
    }

    public void display() {
        System.out.println("Title: " + title +
                "\nAuthor: " + author +
                "\nCopies: " + numberOfCopies +
                "\nISBN: " + isbn);
    }

    public synchronized boolean borrowBook() {
        if (numberOfCopies > 0) {
            numberOfCopies--;
            return true;
        }
        return false;
    }

    public synchronized void returnBook() {
        numberOfCopies++;
    }

    // Getters y setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getIsbn() {
        return isbn;
    }
}
