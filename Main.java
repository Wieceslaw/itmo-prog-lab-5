import processors.*;

import java.util.Scanner;

/**
 * Класс входной точки в программу Main
 */


public class Main {
    /**
     * метод main
     *
     * @param args название файла, с которого будет выполнена загрузка
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CollectionProcessor collectionProcessor = new CollectionProcessor();
        CommandsProcessor mainCommandProcessor = new CommandsProcessor(false, scanner);
        if (args.length > 1) {
            System.out.println("Неверное количество аргументов. Можно ввести только один аргумент - название файла.");
            System.exit(-1);
        } else if (args.length == 1) {
            String fileName = args[0];
            InputProcessor.load(fileName, collectionProcessor);
        }
        while (true) {
            System.out.print("Введите команду: ");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                mainCommandProcessor.executeCommand(line, collectionProcessor);
            } else {
                System.exit(0);
            }
        }
    }
}