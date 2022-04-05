package commands;

import exceptions.WrongArgumentsNumberException;
import processors.CollectionProcessor;

import java.util.Scanner;

/**
 * Класс базовой команды
 *
 */

public abstract class Command {
    private final boolean isScript;
    private final Scanner scanner;

    public Command(boolean isScript, Scanner scanner) {
        this.isScript = isScript;
        this.scanner = scanner;
    }

    public boolean isScript() {
        return isScript;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public abstract void execute(String[] args, CollectionProcessor collectionProcessor) throws WrongArgumentsNumberException;
}