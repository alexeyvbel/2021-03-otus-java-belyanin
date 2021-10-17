package ru.otus.processor;

import java.time.LocalDateTime;

public class DataTimer {
    int getDataTimeSecond(){
        return LocalDateTime.now().getSecond();
    }
}
