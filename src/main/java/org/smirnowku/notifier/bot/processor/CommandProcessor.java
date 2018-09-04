package org.smirnowku.notifier.bot.processor;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.bot.message.MessageSender;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.service.ChatService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandProcessor {

    private final MessageSender messageSender;
    private final ChatService chatService;

    public void process(long chatId, String command) {
        switch (command) {
            case "/start":
                start(chatId);
                break;

            case "/subscribe":
                subscribe(chatId);
                break;
        }
    }

    private void start(long chatId) {
        try {
            chatService.create(new Chat(chatId));
            messageSender.send(chatId, "You have been registered in the service");
        } catch (Exception e) {
            messageSender.send(chatId, "You are already registered in the service");
        }
    }

    private void subscribe(long chatId) {
        messageSender.send(chatId, "Write the name of the channel you want to subscribe to");
    }
}
