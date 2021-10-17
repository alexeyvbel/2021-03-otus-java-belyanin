package ru.otus.listener.homework;

import ru.otus.model.Message;

public class Memento {

    private final Message message;

    public Memento(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

}
