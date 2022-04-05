package commands;

import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

/**
 * Класс команды Clear
 */

public class Clear extends Command {
    public static String name = "clear";
    public static String help = "Очистить коллекцию";

    public Clear(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        collectionProcessor.clear();
        System.out.println("Коллекция очищена.");
    }
}
