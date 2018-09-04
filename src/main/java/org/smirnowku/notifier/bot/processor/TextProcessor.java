package org.smirnowku.notifier.bot.processor;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.bot.message.MessageSender;
import org.smirnowku.notifier.exception.BaseException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TextProcessor {

    private final MessageSender messageSender;

    private final Map<Long, TextMessageReceivedListener> listeners = new HashMap<>();

    public void process(long chatId, String text) {
        TextMessageReceivedListener listener = listeners.get(chatId);
        if (listener != null) {
            try {
                listener.onTextMessageReceived(text);
            } catch (BaseException e) {
                messageSender.send(chatId, e.getMessage());
            }
            removeListener(chatId);
        }
    }

    public void addListener(long chatId, TextMessageReceivedListener listener) {
        listeners.put(chatId, listener);
    }

    public void removeListener(long chatId) {
        listeners.remove(chatId);
    }
}
