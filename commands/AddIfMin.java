package commands;

import elements.Organization;
import exceptions.WrongArgumentsException;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

import static processors.InputProcessor.getElement;

/**
 * Класс команды AddIfMin
 */


public class AddIfMin extends Command {
    public static String name = "add_if_min";
    public static String help = "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";

    public AddIfMin(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        try {
            Organization organization = getElement(this.getScanner(), this.isScript());
            Organization min = collectionProcessor.getMin();
            if (min == null || organization.compareTo(min) < 0) {
                collectionProcessor.add(organization);
                System.out.println("Элемент добавлен");
            } else {
                System.out.println("Элемент не был добавлен");
            }
        } catch (WrongArgumentsException e) {
            System.out.println("Не удалось создать объект");
        }
    }
}
