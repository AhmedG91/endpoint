package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
abstract class FileSystemItem {
    private String name;
    private FileSystemItem parent;

    protected FileSystemItem(String name, FileSystemItem parent) {
        this.name = name;
        this.parent = parent;
    }

    public abstract void delete();

    public String getPath() {
        if (parent == null) {
            return name;
        }
        return parent.getPath() + "/" + name;
    }
}