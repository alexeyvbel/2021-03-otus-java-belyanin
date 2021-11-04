package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

public class ProcessorException implements Processor {
    private final DataTimer dataTimer;

    public ProcessorException(DataTimer dataTimer) {
        this.dataTimer = dataTimer;
    }

    @Override
    public Message process(Message message) {
        if (dataTimer.getDateTime().getSecond() % 2 == 0){
            throw new RuntimeException("Exception on even second");
        }
        return message;
    }
}
