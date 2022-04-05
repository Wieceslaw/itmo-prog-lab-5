package commands;

import elements.Organization;
import exceptions.WrongArgumentsException;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.List;
import java.util.Scanner;

import static processors.InputProcessor.getElement;

/**
 * Класс команды RemoveGrater
 */

public class RemoveGrater extends Command {
    public static String name = "remove_greater";
    public static String help = "Удалить из коллекции все элементы, превышающие заданный";

    public RemoveGrater(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        try {
            int count = 0;
            Organization organization = getElement(this.getScanner(), this.isScript());
            List<Organization> list = collectionProcessor.getSortedList();
            boolean greater = false;
            for (Organization element: list) {
                if (greater) {
                    collectionProcessor.remove(element);
                    count += 1;
                }
                if (element == organization) greater = true;
            }
            if (count == 0) {
                System.out.println("Элементов больше данного не было найдено.");
            } else {
                System.out.println("Удалено элементов " + count);
            }
        } catch (WrongArgumentsException e) {
            System.out.println("Не удалось создать объект.");
        }
    }
}
