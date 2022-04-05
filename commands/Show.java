package commands;

import elements.Organization;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

/**
 * Класс команды Show
 */

public class Show extends Command {
    public static String name = "show";
    public static String help = "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении";

    public Show(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        if (collectionProcessor.isEmpty()) System.out.println("Коллекция пуста.");
        for (Organization organization : collectionProcessor.getCollection()) {
            System.out.println(organization);
        }
    }
}
