package commands;

import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

/**
 * Класс команды Help
 */


public class Help extends Command {
    public static String name = "help";
    public static String help = "Вывести справку по доступным командам";

    public Help(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        System.out.println(Help.name + ": " + Help.help);
        System.out.println(Info.name + ": " + Info.help);
        System.out.println(Show.name + ": " + Show.help);
        System.out.println(Add.name + ": " + Add.help);
        System.out.println(Update.name + ": " + Update.help);
        System.out.println(RemoveById.name + ": " + RemoveById.help);
        System.out.println(Clear.name + ": " + Clear.help);
        System.out.println(Save.name  + ": " + Save.help);
        System.out.println(Exit.name + ": " + Exit.help);
        System.out.println(ExecuteScript.name + ": " + ExecuteScript.help);
        System.out.println(AddIfMax.name + ": " + AddIfMax.help);
        System.out.println(AddIfMin.name + ": " + AddIfMin.help);
        System.out.println(RemoveGrater.name + ": " + RemoveGrater.help);
        System.out.println(SumOfAnnualTurnover.name + ": " + SumOfAnnualTurnover.help);
        System.out.println(PrintDescending.name + ": " + PrintDescending.help);
        System.out.println(PrintFieldAscendingEmployeesCount.name + ": " + PrintFieldAscendingEmployeesCount.help);
    }
}
