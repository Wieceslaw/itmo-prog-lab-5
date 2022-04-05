package commands;

import elements.Organization;
import exceptions.ElementNotFoundException;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

/**
 * Класс команды RemoveById
 */

public class RemoveById extends Command {
    public static String name = "remove_by_id";
    public static String help ="Удалить элемент из коллекции по его id";

    public RemoveById(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length != 1) {
            throw new WrongArgumentsNumberException("1");
        }
        try {
            long id = Long.parseLong(args[0]);
            Organization element = collectionProcessor.getElementById(id);
            if (element != null) {
                collectionProcessor.remove(element);
                System.out.println("Элемент удален.");
            } else {
                throw new ElementNotFoundException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID.");
        } catch (ElementNotFoundException e) {
            System.out.println("Элемента с таким ID не найдено.");
        }
    }
}
