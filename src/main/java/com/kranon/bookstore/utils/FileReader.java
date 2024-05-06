package com.kranon.bookstore.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileReader {

    private final Logger logger = LoggerFactory.getLogger(FileReader.class);

    public byte[] readFromFile() {
        try {
            String folderPath = GlobalVariables.FOLDER_PATH + File.separator + GlobalVariables.FILE_NAME;
            File file = new File(folderPath);
            if (!file.exists()) return null;

            Path path = Paths.get(file.getPath());
            return Files.readAllBytes(path);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
            return null;
        }
    }

    public void writeToFile(String json) {
        File file = new File(GlobalVariables.FOLDER_PATH, GlobalVariables.FILE_NAME);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(json);
        } catch (IOException exception) {
            logger.error(exception.getMessage());
        }
    }
}
