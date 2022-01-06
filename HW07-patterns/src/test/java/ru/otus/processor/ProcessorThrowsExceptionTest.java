package ru.otus.processor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.model.Message;
import ru.otus.processor.homework.ProcessorException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProcessorThrowsExceptionTest {

    @Test
    void processTest(){
        LocalDateTime evenSecondTime = LocalDateTime.of(1,1,1,1,1,2);
        ProcessorException processorException = new ProcessorException(() -> evenSecondTime);

        RuntimeException exception = assertThrows(
                RuntimeException.class, () -> processorException.process(Mockito.mock(Message.class))
        );

        assertEquals("Exception on even second", exception.getMessage());
    }
}
