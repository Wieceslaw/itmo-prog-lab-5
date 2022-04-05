package commands;

import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

/**
 * Класс команды Exit
 */


public class Exit extends Command {
    public static String name = "exit";
    public static String help = "Завершить программу (без сохранения в файл)";

    public Exit(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        System.exit(0);
    }
}
