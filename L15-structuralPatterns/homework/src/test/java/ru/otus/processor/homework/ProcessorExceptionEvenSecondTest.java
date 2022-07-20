package ru.otus.processor.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.model.Message;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ProcessorExceptionEvenSecondTest {

    DateTimeProvider dateTimeProviderMock;
    ProcessorExceptionEvenSecond processor;

    @BeforeEach
    void beforeEach() {
        dateTimeProviderMock = Mockito.mock(DateTimeProvider.class);
        processor = new ProcessorExceptionEvenSecond(dateTimeProviderMock);
    }


    @Test
    void processIfOdd() {
        when(dateTimeProviderMock.getDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1, 1));

        var message = new Message.Builder(1L).field9("field9").build();
        var result = processor.process(message);

        assertThat(result).isEqualTo(message);

    }

    @Test
    void processIfEven() {
        when(dateTimeProviderMock.getDate()).thenReturn(LocalDateTime.of(1, 1, 1, 1, 1, 2));
        var message = new Message.Builder(1L).field9("field9").build();

        assertThrows(RuntimeException.class, () -> processor.process(message));

    }

}