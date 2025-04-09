package src.main.java;
import src.main.java.Model.Library;
import src.main.java.Model.Patron;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.registerBook("The Fault in Our Stars", "John Green", 3, "9780525478812");
        library.registerBook("Looking for Alaska", "John Green", 2, "9780525475064");
        library.registerBook("Paper Towns", "John Green", 1, "9780525426676");
        library.registerBook("An Abundance of Katherines", "John Green", 1, "9780525476887");

        for (int i = 1; i <= 7; i++) {
            String name = "Patron-" + i;
            String details = "Details for user " + i;
            Patron patron = new Patron(name, i, details, library);
            Thread thread = new Thread(patron);
            thread.start();
        }
    }
}


