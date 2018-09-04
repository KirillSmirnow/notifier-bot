package org.smirnowku.notifier.bot.processor;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.bot.message.MessageSender;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.service.ChatService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandProcessor {

    private final ChatService chatService;
    private final MessageSender messageSender;

    public void process(long chatId, String command) {
        switch (command) {
            case "/start":
                start(chatId);
                break;
        }
    }

    private void start(long chatId) {
        chatService.create(new Chat(chatId));
    }
}
