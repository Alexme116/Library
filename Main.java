import Model.Book;
import Model.Library;
import Model.Patron;
import Model.SimulatedPatron;

public class Main {
    public static void main(String[] args) {
        // Crear biblioteca
        Library library = new Library();

        // Agregar libros
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", 2, "ISBN001");
        Book book2 = new Book("1984", "George Orwell", 1, "ISBN002");

        library.addBook(book1);
        library.addBook(book2);

        // Agregar usuarios
        Patron patron1 = new Patron("Alice", 1, "Student");
        Patron patron2 = new Patron("Bob", 2, "Professor");
        Patron patron3 = new Patron("Charlie", 3, "Researcher");

        library.addPatron(patron1);
        library.addPatron(patron2);
        library.addPatron(patron3);

        Thread t1 = new Thread(new SimulatedPatron(library, 1, "ISBN001"));
        Thread t2 = new Thread(new SimulatedPatron(library, 2, "ISBN001"));
        Thread t3 = new Thread(new SimulatedPatron(library, 3, "ISBN001"));
        Thread t4 = new Thread(new SimulatedPatron(library, 1, "ISBN002"));
        Thread t5 = new Thread(new SimulatedPatron(library, 2, "ISBN002"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=== Fin de la simulación ===");
    }
}

