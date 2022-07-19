package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

public class ProcessorExceptionEachEvenSecond implements Processor {
    @Override
    public Message process(Message message) {
        var nowSecond = LocalDateTime.now().getSecond();
        System.out.println("Current second: " + nowSecond);
        if (nowSecond % 2 == 0) {
            throw new RuntimeException("Even second.");
        }
        return message;
    }
}
