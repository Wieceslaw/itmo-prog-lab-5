package commands;

import elements.Organization;
import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс команды Save
 */

public class Save extends Command{
    public static String name = "save";
    public static String help = "Сохранить коллекцию в файл";

    public Save(boolean isScript, Scanner scanner) {
        super(isScript, scanner);
    }

    @Override
    public void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException {
        if (args.length > 0) {
            throw new WrongArgumentsNumberException("0");
        }
        String filePath = "Saves/save.csv";
        try (FileWriter fw = new FileWriter(filePath); CSVWriter writer = new CSVWriter(fw)) {
            for (Organization element : collectionProcessor.getCollection()) {
                String[] line = new String[]{
                        String.valueOf(element.getId()),
                        element.getName(),
                        String.valueOf(element.getCoordinates().getX()),
                        String.valueOf(element.getCoordinates().getY()),
                        element.getAnnualTurnover() == null ? null : String.valueOf(element.getAnnualTurnover()),
                        String.valueOf(element.getEmployeesCount()),
                        element.getType() == null ? null : String.valueOf(element.getType()),
                        element.getOfficialAddress().getStreet(),
                        element.getOfficialAddress().getZipCode()
                };
                writer.writeNext(line);
            }
            System.out.println("Коллекция сохранена.");
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом");
        }
    }
}