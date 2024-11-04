package francescabattistini.springsecurity1.exceptions;

public class UnathorizLoginExeption extends RuntimeException {
    public UnathorizLoginExeption(String message) {
        super(message);
    }
}
