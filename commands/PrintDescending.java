package commands;

import elements.Organization;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Класс команды PrintDescending
 */

public class PrintDescending extends Command {
    public static String name = "print_descending";
    public static String help = "Вывести элементы коллекции в порядке убывания";

    public PrintDescending(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        List<Organization> list = collectionProcessor.getSortedList();
        Collections.reverse(list);
        for (Organization element : list) {
            System.out.println(element);
        }
    }
}
