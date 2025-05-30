package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        FileSystemManager fileSystemManager = new FileSystemManager();
        CommandProcessor commandProcessor = new CommandProcessor(fileSystemManager);
            String input = """
                CREATE fruits
                CREATE vegetables
                CREATE grains
                CREATE fruits/apples
                CREATE fruits/apples/fuji
                LIST
                CREATE grains/squash
                MOVE grains/squash vegetables
                CREATE foods
                MOVE grains foods
                MOVE fruits foods
                MOVE vegetables foods
                LIST
                DELETE fruits/apples
                DELETE foods/fruits/apples
                LIST
                """;

            commandProcessor.processCommandsFromString(input);

    }
}