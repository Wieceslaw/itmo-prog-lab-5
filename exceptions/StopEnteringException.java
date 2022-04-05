package exceptions;

/**
 * Класс-исключение для возможной остановки пользовательского ввода при помощи ключевого слова stop
 */
public class StopEnteringException extends Exception{
    public StopEnteringException() {
    }

    public StopEnteringException(String message) {
        super(message);
    }

    public StopEnteringException(String message, Throwable cause) {
        super(message, cause);
    }

    public StopEnteringException(Throwable cause) {
        super(cause);
    }
}