package Model;

public class SimulatedPatron implements Runnable {
    private final Library library;
    private final int patronId;
    private final String isbn;

    public SimulatedPatron(Library library, int patronId, String isbn) {
        this.library = library;
        this.patronId = patronId;
        this.isbn = isbn;
    }

    @Override
    public void run() {
        try {
            // Simula intento de tomar el libro
            library.borrowBook(patronId, isbn);

            // Simula un tiempo aleatorio de lectura
            Thread.sleep((long) (Math.random() * 2000 + 1000)); // 1-3 segundos

            // Lo devuelve
            library.returnBook(patronId, isbn);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
