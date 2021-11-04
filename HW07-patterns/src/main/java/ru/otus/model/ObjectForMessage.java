package ru.otus.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectForMessage implements Cloneable{

    private List<String> data;

    public ObjectForMessage(){
        data = new ArrayList<>();
    }

    public ObjectForMessage(List<String> data) {
        this.data = data;
    }

    @Override
    public ObjectForMessage clone() {
        return data != null ? new ObjectForMessage(List.copyOf(data)) : new ObjectForMessage();
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}

