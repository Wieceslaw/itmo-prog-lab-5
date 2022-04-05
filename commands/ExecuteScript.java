package commands;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;
import processors.CommandsProcessor;

/**
 * Класс команды ExecuteScript
 */

public class ExecuteScript extends Command {
    public static Stack<String> sciptsStack = new Stack<String>();
    public static String name = "execute_script";
    public static String help = "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит";

    public ExecuteScript(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length != 1) {
            throw new WrongArgumentsNumberException("1");
        }
        String fileName = args[0];
        if (sciptsStack.contains(fileName)) {
            System.out.println("Скрипт " + Paths.get(fileName).getFileName() + " уже вызван.");
        } else {
            sciptsStack.push(fileName);
            try {
                Scanner newScanner = new Scanner(new File(fileName));
                CommandsProcessor commandsProcessor = new CommandsProcessor(true, newScanner);
                while (newScanner.hasNextLine()) {
                    commandsProcessor.executeCommand(newScanner.nextLine().trim(), collectionProcessor);
                }
                if (!isScript()) System.out.println("Файл " + Paths.get(fileName).getFileName() + " успешно исполнен.");
            } catch (NullPointerException | FileNotFoundException e) {
                System.out.println("Нет указанного файла " + e.getMessage());
            }
            sciptsStack.pop();
        }
    }
}