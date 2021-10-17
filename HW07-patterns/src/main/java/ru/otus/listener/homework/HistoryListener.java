package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.util.*;

public class HistoryListener implements Listener, HistoryReader {

    Map<Long, List<Memento>> history = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        if (history.containsKey(msg.getId())){
            history.get(msg.getId()).add(new Memento(msg.clone()));
        } else {
            history.put(msg.getId(),List.of(new Memento(msg.clone())));
        }
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return history.containsKey(id)
                ? Optional.ofNullable(history.get(id).get(history.size()).getMessage())
                : Optional.empty();
    }
}
