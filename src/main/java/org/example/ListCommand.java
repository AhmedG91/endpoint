package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
class ListCommand implements Command {
    private final FileSystemManager fileSystemManager;
    private CommandResult result;

    public ListCommand(FileSystemManager fileSystemManager) {
        this.fileSystemManager = fileSystemManager;
    }

    @Override
    public CommandResult execute() {
        try {
            List<String> listing = fileSystemManager.listDirectories();
            StringBuilder sb = new StringBuilder("LIST\n");
            listing.forEach(line -> sb.append(line).append("\n"));
            result = new CommandResult(true, sb.toString());
        } catch (Exception e) {
            result = new CommandResult(false, e.getMessage());
        }
        return result;
    }

}