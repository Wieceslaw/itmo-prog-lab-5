package commands;

import exceptions.*;
import processors.CollectionProcessor;

import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Класс команды Info
 */


public class Info extends Command {
    public static String name = "info";
    public static String help = "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";

    public Info(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        String pattern = "dd/M/yyyy hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.println("Тип: " + collectionProcessor.getCollection().getClass().getName());
        System.out.println("Дата инициализации: " + simpleDateFormat.format(collectionProcessor.getCreateDate()));
        System.out.println("Количество элементов: " + collectionProcessor.getSize());
    }
}
