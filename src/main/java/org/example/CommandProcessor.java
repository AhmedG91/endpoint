package org.example;

import java.util.Arrays;
import java.util.Scanner;

class CommandProcessor {

    private final FileSystemManager fileSystemManager;

    public CommandProcessor(FileSystemManager fileSystemManager) {
        this.fileSystemManager = fileSystemManager;
    }

    public void processCommandsFromString(String input) {
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);

            Command cmd = parseCommand(command, args);

            if (cmd != null) {
                CommandResult result = cmd.execute();
                System.out.print(result.getMessage());
                if(!result.getMessage().endsWith("\n")) {
                    System.out.println();
                }
            } else {
                System.out.println("Invalid command: " + command);
            }
        }

        scanner.close();
    }

    private Command parseCommand(String command, String[] args) {
        switch (command) {
            case "CREATE":
                if (args.length == 1) {
                    return new CreateCommand(args[0], fileSystemManager);
                }
                break;
            case "MOVE":
                if (args.length == 2) {
                    return new MoveCommand(args[0], args[1], fileSystemManager);
                }
                break;
            case "DELETE":
                if (args.length == 1) {
                    return new DeleteCommand(args[0], fileSystemManager);
                }
                break;
            case "LIST":
                if (args.length == 0) {
                    return new ListCommand(fileSystemManager);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
        return null;
    }

}