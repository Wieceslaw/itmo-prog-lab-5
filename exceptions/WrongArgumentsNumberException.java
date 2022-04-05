package exceptions;

/**
 * Класс-исключение - вызывается, когда в команду передается неправильное число аргументов
 */

public class WrongArgumentsNumberException extends Exception{
    public WrongArgumentsNumberException() {
    }

    public WrongArgumentsNumberException(String message) {
        super(message);
    }

    public WrongArgumentsNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongArgumentsNumberException(Throwable cause) {
        super(cause);
    }
}
