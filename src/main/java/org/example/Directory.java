package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.*;

@Getter
@Setter
@ToString(callSuper = true)
public class Directory extends FileSystemItem {

    private Map<String, FileSystemItem> children = new HashMap<>();

    public Directory(String name, FileSystemItem parent) {
        super(name, parent);
    }

    public void add(FileSystemItem component) {
        children.put(component.getName(), component);
    }

    public void remove(String name) {
        children.remove(name);
    }

    public FileSystemItem getChild(String name) {
        return children.get(name);
    }

    @Override
    public void delete() {
        children.values().forEach(FileSystemItem::delete);
        children.clear();

        if(getParent() != null) {
            ((Directory) getParent()).remove(this.getName());
        }
    }
}