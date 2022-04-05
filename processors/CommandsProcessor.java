package processors;

import commands.*;
import exceptions.WrongArgumentsNumberException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Класс обрабатывающий команды
 */

public class CommandsProcessor {
    private final HashMap<String, Command> commands = new HashMap<>();
    private boolean isScript;
    private Scanner scanner;

    /**
     * Конструктор класса
     * @param isScript если {@code true}, то происходит считывание со скрипт-файла, если {@code false}, то с командной строки
     * @param scanner сканер, с которого считываются команды
     */
    public CommandsProcessor(boolean isScript, Scanner scanner) {
        setScript(isScript);
        setScanner(scanner);
        commands.put("help", new Help(isScript, scanner));
        commands.put("info", new Info(isScript, scanner));
        commands.put("show", new Show(isScript, scanner));
        commands.put("add", new Add(isScript, scanner));
        commands.put("update", new Update(isScript, scanner));
        commands.put("remove_by_id", new RemoveById(isScript, scanner));
        commands.put("clear", new Clear(isScript, scanner));
        commands.put("save", new Save(isScript, scanner));
        commands.put("execute_script", new ExecuteScript(isScript, scanner));
        commands.put("exit", new Exit(isScript, scanner));
        commands.put("add_if_max", new AddIfMax(isScript, scanner));
        commands.put("add_if_min", new AddIfMin(isScript, scanner));
        commands.put("remove_greater", new RemoveGrater(isScript, scanner));
        commands.put("sum_of_annual_turnover", new SumOfAnnualTurnover(isScript, scanner));
        commands.put("print_descending", new PrintDescending(isScript, scanner));
        commands.put("print_field_ascending_employees_count", new PrintFieldAscendingEmployeesCount(isScript, scanner));
    }

    /**
     *
     * @param commandLine строка с командой и ее аргументами
     * @param collectionProcessor обработчик коллекции, с которым ведется работа
     */
    public void executeCommand(String commandLine, CollectionProcessor collectionProcessor) {
        String[] args = commandLine.split("\\s+");
        String commandName = args[0];
        args = Arrays.copyOfRange(args, 1, args.length);
        if (commands.containsKey(commandName)) {
            try{
                commands.get(commandName).execute(args, collectionProcessor);
            } catch (WrongArgumentsNumberException e) {
                System.out.println("Команда принимает число аргументов: " + e.getMessage());
            }
        } else {
            System.out.println("Такой команды нет. Введите help, чтобы получить список и описание команд.");
        }
    }

    /**
     *
     * @return словарь с объектами команд {@code Command}
     */
    public HashMap<String, Command> getCommands() {
        return this.commands;
    }

    /**
     *
     * @param script идет считывание со скрипта
     */
    public void setScript(boolean script) {
        this.isScript = script;
    }

    /**
     *
     * @param scanner сканер, с которого будет происходить считывание команд
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     *
     * @return идет ли считывание со скрипта
     */
    public boolean isScript() {
        return isScript;
    }

    /**
     *
     * @return сканер, с которого происходит считывание команд
     */
    public Scanner getScanner() {
        return scanner;
    }
}
