package commands;

import elements.Organization;
import exceptions.ElementNotFoundException;
import exceptions.WrongArgumentsException;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

import static processors.InputProcessor.getElement;

/**
 * Класс команды Update
 */

public class Update extends Command {
    public static String name = "update";
    public static String help = "Обновить значение элемента коллекции, id которого равен заданному";

    public Update(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length != 1) {
            throw new WrongArgumentsNumberException("1");
        }
        try {
            Long id = Long.parseLong(args[0]);
            Organization element = collectionProcessor.getElementById(id);
            if (element == null) throw new ElementNotFoundException();
            collectionProcessor.remove(element);
            Organization organization = getElement(this.getScanner(), this.isScript());
            organization.setId(id);
            collectionProcessor.add(organization);
            System.out.println("Элемент обновлен.");
        } catch (WrongArgumentsException e) {
            System.out.println("Не удалось создать объект");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        } catch (ElementNotFoundException e) {
            System.out.println("Элемента с таким ID не найдено.");
        }
    }
}