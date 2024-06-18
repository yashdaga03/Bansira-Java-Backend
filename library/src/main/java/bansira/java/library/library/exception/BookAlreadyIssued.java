package bansira.java.library.library.exception;

public class BookAlreadyIssued extends RuntimeException {
    public BookAlreadyIssued(String message) {
        super(message);
    }
}
