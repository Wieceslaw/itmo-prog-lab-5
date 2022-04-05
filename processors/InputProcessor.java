package processors;

import elements.Address;
import elements.Coordinates;
import elements.Organization;
import elements.OrganizationType;
import exceptions.WrongArgumentsException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;


/**
 * Класс обработчик пользовательского ввода
 */
public class InputProcessor {
    /**
     * Загружает данные в коллекцию из файла
     *
     * @param filePath путь к файлу, с которого ведется загрузка
     * @param collectionProcessor обработчик коллекции, через который ведется работа с коллекцией
     */
    public static void load(String filePath, CollectionProcessor collectionProcessor) {
        if (!Objects.equals(filePath.split("\\.")[filePath.split("\\.").length - 1], "csv")) {
            System.out.println("Неверный формат файла. Используйте формат csv.");
            System.exit(-1);
        }
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            CSVParser parser = new CSVParserBuilder().build();
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withCSVParser(parser).build();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                Organization organization = parseCsvLine(line);
                if (organization != null) {
                    collectionProcessor.add(organization);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка. Файл не найден.");
            System.exit(-1);
        } catch (CsvValidationException | IOException e) {
            System.out.println("Ошибка чтения файла.");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Парсер строки полей класса из csv-файла
     *
     * @param line массив строк - полей создаваемого класса
     * @return объект класса {@code Organisation}, с заданными полями, или {@code null}, если не удалось создать объект
     */
    private static Organization parseCsvLine(String[] line) {
        try {
            if (line.length != 9) {
                throw new WrongArgumentsException("Недостаточное число аргументов.");
            }
            Long id = Long.parseLong(line[0]);
            String name = line[1];
            Coordinates coordinates = new Coordinates(Double.parseDouble(line[2].replace(',', '.')), Float.parseFloat(line[3].replace(',', '.')));
            Integer annualTurnover = Objects.equals(line[4], "") ? null : Integer.parseInt(line[4]);
            int employeesCount = Integer.parseInt(line[5]);
            OrganizationType type = line[6].equals("") ? null : OrganizationType.valueOf(line[6]);
            String street = line[7].equals("") ? null : line[7];
            String zipCode = line[8].equals("") ? null : line[8];
            Address address = new Address(street, zipCode);
            return new Organization(id, name, coordinates, annualTurnover, employeesCount, type, address);
        } catch (Exception e) {
            System.out.println("Неправильные данные в строке файла. Cтрока пропущена. " + e.getMessage());
        }
        return null;
    }

    /** Метод для получения элемента из вводимых строк
     *
     * @param scanner сканер, с которого ведется считывание строк
     * @param isScript считываются ли строки со скрипта
     * @return объект класса {@code Organisation}, с заданными полями, или {@code null}, если не удалось создать объект
     * @throws WrongArgumentsException ошибка введенных аргументов - не удалось создать объект с такими полями
     */
    public static Organization getElement(Scanner scanner, boolean isScript) throws WrongArgumentsException {
        return new Organization(
                getName(scanner, isScript),
                new Coordinates(getX(scanner, isScript), getY(scanner, isScript)),
                getAnnualTurnover(scanner, isScript),
                getEmployeesCount(scanner, isScript),
                getType(scanner, isScript),
                new Address(getStreet(scanner, isScript), getZipCode(scanner, isScript))
        );
    }

    /**
     *
     * @param scanner сканер, с которого ведется считывание строк
     * @param isScript считываются ли строки со скрипта - нужно ли выводить строки при работе с пользователем
     * @return поле {@code Organisation name}
     */
    private static String getName(Scanner scanner, boolean isScript) {
        if (!isScript) System.out.print("Введите название организации (непустая, строка): ");
        String name;
        try {
            String line = scanner.nextLine().trim();
            line = line.isEmpty() ? null : line;
            name = line;
            if (name == null) {
                throw new WrongArgumentsException();
            }
        } catch (NoSuchElementException e) {
            name = "";
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) System.out.println("Ошибка ввода: введите корректное значение.");
            return getName(scanner, isScript);
        }
        return name;
    }

    /**
     *
     * @param scanner сканер, с которого ведется считывание строк
     * @param isScript считываются ли строки со скрипта - нужно ли выводить строки при работе с пользователем
     * @return поле {@code Coordinates x}
     */
    private static Double getX(Scanner scanner, boolean isScript) {
        if (!isScript) System.out.print("Введите координату x организации (непустая, double, не больше 76): ");
        Double x;
        try {
            String line = scanner.nextLine().trim();
            line = line.isEmpty() ? null : line;
            x = Double.parseDouble(line.replace(',', '.'));
            if (x > 76) {
                throw new WrongArgumentsException();
            }
        } catch (NoSuchElementException e) {
            x = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) System.out.println("Ошибка ввода: введите корректное значение. " + e);
            return getX(scanner, isScript);
        }
        return x;
    }

    /**
     *
     * @param scanner сканер, с которого ведется считывание строк
     * @param isScript считываются ли строки со скрипта - нужно ли выводить строки при работе с пользователем
     * @return поле {@code Coordinates y}
     */
    private static Float getY(Scanner scanner, boolean isScript) {
        if (!isScript) System.out.print("Введите координату y организации (непустая, float): ");
        Float y;
        try {
            String line = scanner.nextLine().trim();
            line = line.isEmpty() ? null : line;
            y = Float.parseFloat(line.replace(',', '.'));
        } catch (NoSuchElementException e) {
            y = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) System.out.println("Ошибка ввода: введите корректное значение.");
            return getY(scanner, isScript);
        }
        return y;
    }

    /**
     *
     * @param scanner сканер, с которого ведется считывание строк
     * @param isScript считываются ли строки со скрипта - нужно ли выводить строки при работе с пользователем
     * @return поле {@code Organisation annualTurnover}
     */
    private static Integer getAnnualTurnover(Scanner scanner, boolean isScript) {
        if (!isScript) System.out.print("Введите годовой оборот annualTurnover организации (integer, больше 0): ");
        Integer annualTurnover;
        try {
            String line = scanner.nextLine().trim();
            line = line.isEmpty() ? null : line;
            if (line == null) {
                return null;
            }
            annualTurnover = Integer.parseInt(line);
            if (annualTurnover <= 0) {
                throw new WrongArgumentsException();
            }
        } catch (NoSuchElementException e) {
            annualTurnover = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) System.out.println("Ошибка ввода: введите корректное значение.");
            return getAnnualTurnover(scanner, isScript);
        }
        return annualTurnover;
    }

    /**
     *
     * @param scanner сканер, с которого ведется считывание строк
     * @param isScript считываются ли строки со скрипта - нужно ли выводить строки при работе с пользователем
     * @return поле {@code Organisation employeesCount}
     */
    private static int getEmployeesCount(Scanner scanner, boolean isScript) {
        if (!isScript) System.out.print("Введите количество сотрудников employeesCount организации (int, больше 0): ");
        int employeesCount;
        try {
            String line = scanner.nextLine().trim();
            line = line.isEmpty() ? null : line;
            employeesCount = Integer.parseInt(line);
            if (employeesCount <= 0) {
                throw new WrongArgumentsException();
            }
        } catch (NoSuchElementException e) {
            employeesCount = 0;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) System.out.println("Ошибка ввода: введите корректное значение.");
            return getEmployeesCount(scanner, isScript);
        }
        return employeesCount;
    }

    /**
     *
     * @param scanner сканер, с которого ведется считывание строк
     * @param isScript считываются ли строки со скрипта - нужно ли выводить строки при работе с пользователем
     * @return поле {@code Organisation type}
     */
    private static OrganizationType getType(Scanner scanner, boolean isScript) {
        if (!isScript) System.out.print("Введите тип организации type организации (один из: COMMERCIAL, PUBLIC, TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY): ");
        OrganizationType type;
        try {
            String line = scanner.nextLine().trim();
            line = line.isEmpty() ? null : line;
            if (line == null) {
                return null;
            }
            type = OrganizationType.valueOf(line);
        } catch (NoSuchElementException e) {
            type = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) System.out.println("Ошибка ввода: введите корректное значение.");
            return getType(scanner, isScript);
        }
        return type;
    }

    /**
     *
     * @param scanner сканер, с которого ведется считывание строк
     * @param isScript считываются ли строки со скрипта - нужно ли выводить строки при работе с пользователем
     * @return поле {@code Address street}
     */
    private static String getStreet(Scanner scanner, boolean isScript) {
        if (!isScript) System.out.print("Введите название улицы street организации (строка): ");
        String street;
        try {
            String line = scanner.nextLine().trim();
            line = line.isEmpty() ? null : line;
            street = line;
        } catch (NoSuchElementException e) {
            street = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) System.out.println("Ошибка ввода: введите корректное значение.");
            return getStreet(scanner, isScript);
        }
        return street;
    }

    /**
     *
     * @param scanner сканер, с которого ведется считывание строк
     * @param isScript считываются ли строки со скрипта - нужно ли выводить строки при работе с пользователем
     * @return поле {@code Address zipCode}
     */
    private static String getZipCode(Scanner scanner, boolean isScript) {
        if (!isScript) System.out.print("Введите зип код zipCode организации (строка): ");
        String zipCode;
        try {
            String line = scanner.nextLine().trim();
            line = line.isEmpty() ? null : line;
            zipCode = line;
        } catch (NoSuchElementException e) {
            zipCode = null;
            System.exit(1);
        } catch (Exception e) {
            if (!isScript) System.out.println("Ошибка ввода: введите корректное значение.");
            return getZipCode(scanner, isScript);
        }
        return zipCode;
    }
}