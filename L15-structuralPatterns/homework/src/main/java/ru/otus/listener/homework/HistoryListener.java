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
        var loggedMessage = message.toBuilder()
                .field13(message.getField13().copy())
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
