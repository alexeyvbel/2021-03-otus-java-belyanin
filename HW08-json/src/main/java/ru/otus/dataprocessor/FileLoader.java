package ru.otus.dataprocessor;

import com.google.gson.Gson;
import ru.otus.model.Measurement;

import java.io.*;
import java.util.List;

public class FileLoader implements Loader {
    private final String fileName;

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {

        String filePath = getClass().getClassLoader().getResource(fileName).getPath();
        try (var reader = new InputStreamReader(new FileInputStream(filePath))){
            return List.of(new Gson().fromJson(reader,Measurement[].class));
        }
        catch (IOException e) {
            throw new FileProcessException(e);
        }
        //читает файл, парсит и возвращает результ
    }
}
