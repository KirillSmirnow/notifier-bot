package org.smirnowku.notifier.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotifierLongPollingBot extends TelegramLongPollingBot {

    private final NotifierBotProperties properties;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            int userId = 0;
            if (message.isCommand()) {
                processCommand(userId, message.getText());
            } else {
                processText(userId, message.getText());
            }
        }
    }

    @Override
    public String getBotToken() {
        return properties.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return properties.getBotUsername();
    }

    private void processCommand(int userId, String command) {
        log.info("Command {} from {}", command, userId);
    }

    private void processText(int userId, String text) {
        log.info("Text {} from {}", text, userId);
    }
}
