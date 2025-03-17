package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
class FileSystemManager {

    private final Directory root;
    private final Map<String, FileSystemItem> pathCache = new HashMap<>();

    public FileSystemManager() {
        root = new Directory("/", null);
        pathCache.put("/", root);
    }

    public void createDirectory(String path) {
        String[] parts = path.split("/");
        if (parts.length < 1) {
            throw new IllegalArgumentException("Invalid path: " + path);
        }

        String directoryName = parts[parts.length - 1];
        String parentPath = "/";

        if (parts.length > 1) {
            parentPath = path.substring(0, path.lastIndexOf('/'));
        }

        FileSystemItem parent = pathCache.get(parentPath);

        if (parent == null || !(parent instanceof Directory)) {
            throw new IllegalArgumentException(parentPath + " does not exist");
        }

        if (((Directory) parent).getChildren().containsKey(directoryName)) {
            throw new IllegalArgumentException(path + " already exists");
        }

        Directory newDirectory = new Directory(directoryName, parent);
        ((Directory) parent).add(newDirectory);
        pathCache.put(path, newDirectory);
    }

    public void moveDirectory(String sourcePath, String destinationPath) {
        FileSystemItem source = pathCache.get(sourcePath);
        FileSystemItem destination = pathCache.get(destinationPath);

        if (source == null) {
            throw new IllegalArgumentException(sourcePath + " does not exist");
        }

        if (destination == null || !(destination instanceof Directory)) {
            throw new IllegalArgumentException(destinationPath + " does not exist");
        }

        if (destination.getPath().startsWith(sourcePath + "/")) {
            throw new IllegalArgumentException("Cannot move " + sourcePath + " into its own subdirectory");
        }

        Directory sourceDirParent = (Directory) source.getParent();
        if (sourceDirParent != null) {
            sourceDirParent.remove(source.getName());
            ((Directory)destination).add(source);
            source.setParent(destination);

            updatePathCacheAfterMove(source, sourcePath, destinationPath);
        }
    }

    public void deleteDirectory(String path) {
        FileSystemItem item = pathCache.get(path);
        if (item == null) {
            int lastSlashIndex = path.lastIndexOf('/');
            if (lastSlashIndex > 0) {
                String parentPath = path.substring(0, lastSlashIndex);

                FileSystemItem parent = pathCache.get(parentPath);
                if (parent == null) {
                    String firstComponent = path.split("/")[0];
                    if (!pathCache.containsKey(firstComponent)) {
                        throw new IllegalArgumentException(firstComponent + " does not exist");
                    }
                }
            }
            throw new IllegalArgumentException(path + " does not exist");
        }

        FileSystemItem parent = item.getParent();

        if (parent instanceof Directory directory) {
            directory.remove(item.getName());
        }

        removePathFromCache(path);
    }
    public List<String> listDirectories() {
        return listDirectoriesRecursive(root, 0);
    }

    private List<String> listDirectoriesRecursive(Directory directory, int depth) {
        List<String> listing = new ArrayList<>();

        List<String> sortedKeys = directory.getChildren().keySet().stream().sorted().toList();

        for (String childName : sortedKeys) {
            FileSystemItem child = directory.getChild(childName);
            if (child instanceof Directory) {
                StringBuilder indent = new StringBuilder();
                for (int i = 0; i < depth; i++) {
                    indent.append("  ");
                }
                listing.add(indent + childName);
                listing.addAll(listDirectoriesRecursive((Directory) child, depth + 1));
            }
        }

        return listing;
    }

    private void updatePathCacheAfterMove(FileSystemItem movedItem, String oldPath, String newParentPath) {
        String newPath = newParentPath + "/" + movedItem.getName();
        pathCache.remove(oldPath);
        pathCache.put(newPath, movedItem);

        if (movedItem instanceof Directory movedDirectory) {
            for (FileSystemItem child : movedDirectory.getChildren().values()) {
                String oldChildPath = oldPath + "/" + child.getName();
                updatePathCacheAfterMove(child, oldChildPath, newPath);
            }
        }
    }


    private void removePathFromCache(String path) {
        List<String> pathsToRemove = new ArrayList<>();
        pathsToRemove.add(path);

        for (String cachePath : new ArrayList<>(pathCache.keySet())) {
            if (cachePath.startsWith(path + "/")) {
                pathsToRemove.add(cachePath);
            }
        }

        for (String pathToRemove : pathsToRemove) {
            pathCache.remove(pathToRemove);
        }
    }

}