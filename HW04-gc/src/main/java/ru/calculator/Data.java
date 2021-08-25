package ru.calculator;

public class Data {
    public Data() {
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;

    public Data(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
