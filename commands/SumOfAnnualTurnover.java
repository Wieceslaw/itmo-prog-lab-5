package commands;

import elements.Organization;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

/**
 * Класс команды SumOfAnnualTurnover
 */

public class SumOfAnnualTurnover extends Command {
    public static String name = "sum_of_annual_turnover";
    public static String help = "Вывести сумму значений поля annualTurnover для всех элементов коллекции";

    public SumOfAnnualTurnover(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        Integer sum = 0;
        for (Organization element : collectionProcessor.getCollection()) {
            sum += element.getAnnualTurnover();
        }
        System.out.println(sum);
    }
}
