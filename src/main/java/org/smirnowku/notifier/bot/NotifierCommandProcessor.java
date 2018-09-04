package org.smirnowku.notifier.bot;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.service.ChatService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotifierCommandProcessor {

    private final ChatService chatService;

    public void process(String command, long chatId, NotifierBotMessageSender messageSender) {
        switch (command) {
            case "/start":
                start(chatId, messageSender);
                break;
        }
    }

    private void start(long chatId, NotifierBotMessageSender messageSender) {
        chatService.create(new Chat(chatId));
        messageSender.sendMessage("You have been registered in the service");
    }
}
