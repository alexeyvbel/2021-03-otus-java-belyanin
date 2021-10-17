package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorException implements Processor {
    private final DataTimer dataTimer;

    public ProcessorException(DataTimer dataTimer) {
        this.dataTimer = dataTimer;
    }

    @Override
    public Message process(Message message) {
        int dataTimeSecond = dataTimer.getDataTimeSecond();
        if (dataTimeSecond % 2 == 0){
            throw new RuntimeException("Exception on even second");
        }
        return message;
    }
}
