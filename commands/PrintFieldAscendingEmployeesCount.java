package commands;

import elements.Organization;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.*;

/**
 * Класс команды PrintFieldAscendingEmployeesCount
 */

public class PrintFieldAscendingEmployeesCount extends Command {
    public static String name = "print_field_ascending_employees_count";
    public static String help = "Вывести значения поля employeesCount всех элементов в порядке возрастания";

    public PrintFieldAscendingEmployeesCount(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        List<Organization> list = new ArrayList<>(collectionProcessor.getCollection());
        list.sort(Comparator.comparingInt(Organization::getEmployeesCount));
        for (Organization element : list) {
            System.out.println(element.getEmployeesCount());
        }
    }
}
