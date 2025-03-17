package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class DeleteCommand implements Command {
    private final String path;
    private final FileSystemManager fileSystemManager;
    private CommandResult result;

    public DeleteCommand(String path, FileSystemManager fileSystemManager) {
        this.path = path;
        this.fileSystemManager = fileSystemManager;
    }

    @Override
    public CommandResult execute() {
        try {
            fileSystemManager.deleteDirectory(path);
            result = new CommandResult(true, "DELETE " + path);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            if (errorMessage == null) {
                errorMessage = "Unknown error occurred";
            }
            String message = "Cannot delete " + path + " - " + errorMessage;
            result = new CommandResult(false, message);
        }
        return result;
    }

}