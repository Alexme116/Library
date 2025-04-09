package src.main.java.Model;

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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public synchronized boolean borrow() {
        if (numberOfCopies > 0) {
            numberOfCopies--;
            return true;
        }
        return false;
    }

    public synchronized boolean returnBook() {
        numberOfCopies++;
        return true;
    }
}
