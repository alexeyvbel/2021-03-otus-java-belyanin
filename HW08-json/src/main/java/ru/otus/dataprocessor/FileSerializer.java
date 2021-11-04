package ru.otus.dataprocessor;

import com.google.gson.Gson;

import java.io.*;
import java.util.Map;

public class FileSerializer implements Serializer {
    private String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        //формирует результирующий json и сохраняет его в файл
        try(Writer writer = new OutputStreamWriter(new FileOutputStream(fileName))){
            writer.write(new Gson().toJson(data));
        } catch (IOException e) {
            throw new FileProcessException(e);
        }
    }
}
