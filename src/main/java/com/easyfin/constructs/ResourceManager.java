package com.easyfin.constructs;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * General resource manager for saving/loading serialized data.
 */
public class ResourceManager {
    /**
     * Save a serializable object to a file for persistent storage.
     *
     * @param data the data to be stored
     * @param fileName the name/path of the file to be saved, starting in the root directory
     * @throws IOException if the object cannot be saved for any reason
     */
    public static void save(Serializable data, String fileName) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            oos.writeObject(data);
        }
    }

    /**
     * Loads a previously serialized object by filename.
     *
     * @param fileName the name of the object to be loaded
     * @return the object that has been loaded
     * @throws IOException if the file cannot be opened for any reason
     * @throws ClassNotFoundException if the serialization target cannot be found
     */
    public static Object load(String fileName) throws IOException, ClassNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
            return ois.readObject();
        }
    }
}
