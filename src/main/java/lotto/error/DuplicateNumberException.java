package lotto.error;

public class DuplicateNumberException extends RuntimeException {
    public DuplicateNumberException(String message) {
        super(message);
    }
}
