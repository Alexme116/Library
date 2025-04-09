package src.main.java.Model;

import java.util.Random;

public class Patron implements Runnable {
    private String name;
    public int id;
    private String details;
    private final Library library;
    private final Random random = new Random();

    public Patron(String name, int id, String details, Library library) {
        this.name = name;
        this.id = id;
        this.details = details;
        this.library = library;
    }

    @Override
    public void run() {
        try {
            String title = library.getRandomBookTitle();

            if (title == null) {
                System.out.println(nameWithId() + " found no books to borrow.");
                return;
            }

            boolean success = library.borrowBook(title, nameWithId());
            Thread.sleep(random.nextInt(3000) + 1000);

            if (success) {
                library.returnBook(title, nameWithId());
            }
        } catch (InterruptedException e) {
            System.out.println(nameWithId() + " was interrupted.");
        }
    }

    private String nameWithId() {
        return name + " (ID: " + id + ")";
    }

    // Getters if needed
    public String getDetails() {
        return details;
    }

    public String getName() {
        return name;
    }
}
