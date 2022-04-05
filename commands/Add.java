package commands;

import exceptions.WrongArgumentsException;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

import static processors.InputProcessor.getElement;

/**
 * Класс команды Add
 */

public class Add extends Command {
    public static String name = "add";
    public static String help = "Добавить новый элемент в коллекцию";

    public Add(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        try {
            collectionProcessor.add(getElement(this.getScanner(), this.isScript()));
            System.out.println("Элемент добавлен.");
        } catch (WrongArgumentsException e) {
            System.out.println("Не удалось создать объект.");;
        }
    }
}
