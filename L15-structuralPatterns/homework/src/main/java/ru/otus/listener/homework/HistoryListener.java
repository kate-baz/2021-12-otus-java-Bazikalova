package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {
    private final Deque<Message> stack = new ArrayDeque<>();

    @Override
    public void onUpdated(Message message) {
        var loggedMessage = new Message.Builder(message.getId())
                .field1(message.getField1())
                .field2(message.getField2())
                .field3(message.getField3())
                .field4(message.getField4())
                .field5(message.getField5())
                .field6(message.getField6())
                .field7(message.getField7())
                .field8(message.getField8())
                .field9(message.getField9())
                .field10(message.getField10())
                .field11(message.getField11())
                .field12(message.getField12())
                .field13(message.getField13())
                .build();
        stack.add(loggedMessage);
        System.out.println("Message saved to log");
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        for (Message message : stack) {
            var currentMessageId = message.getId();
            if (currentMessageId == id) {
                return Optional.of(message);
            }
        }
        return Optional.empty();
    }
}
