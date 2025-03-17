package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MoveCommand implements Command {
    private final String sourcePath;
    private final String destinationPath;
    private final FileSystemManager fileSystemManager;
    private CommandResult result;

    public MoveCommand(String sourcePath, String destinationPath, FileSystemManager fileSystemManager) {
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
        this.fileSystemManager = fileSystemManager;
    }

    @Override
    public CommandResult execute() {
        try {
            fileSystemManager.moveDirectory(sourcePath, destinationPath);
            result = new CommandResult(true, "MOVE " + sourcePath + " " + destinationPath);
        } catch (Exception e) {
            result = new CommandResult(false, e.getMessage());
        }
        return result;
    }

}