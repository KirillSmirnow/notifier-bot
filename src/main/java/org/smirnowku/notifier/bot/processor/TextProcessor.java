package org.smirnowku.notifier.bot.processor;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.bot.message.MessageSender;
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
            listener.onTextMessageReceived(text);
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
