package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CreateCommand implements Command {
    private final String path;
    private final FileSystemManager fileSystemManager;
    private CommandResult result;

    public CreateCommand(String path, FileSystemManager fileSystemManager) {
        this.path = path;
        this.fileSystemManager = fileSystemManager;
    }

    @Override
    public CommandResult execute() {
        try {
            fileSystemManager.createDirectory(path);
            result = new CommandResult(true, "CREATE " + path);
        } catch (Exception e) {
            result = new CommandResult(false, e.getMessage());
        }
        return result;
    }

}