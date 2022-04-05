package exceptions;

/**
 * Класс-исключение - вызывается, когда удаляемый элемент не найден
 */

public class ElementNotFoundException extends Exception{
    public ElementNotFoundException() {
    }

    public ElementNotFoundException(String message) {
        super(message);
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }
}