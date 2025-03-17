package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class CommandResult {
    private boolean success;
    private String message;

    public CommandResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}