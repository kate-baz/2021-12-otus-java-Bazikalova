package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

public class ProcessorExceptionEvenSecond implements Processor {

    private DateTimeProvider dateTimeProvider;

    public ProcessorExceptionEvenSecond(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        var nowSecond = dateTimeProvider.getDate().getSecond();
        if (nowSecond % 2 == 0) {
            throw new RuntimeException("Even second.");
        }
        return message;
    }
}
