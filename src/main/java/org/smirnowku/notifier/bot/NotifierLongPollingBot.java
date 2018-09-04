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
public class NotifierLongPollingBot extends TelegramLongPollingBot implements NotifierBotMessageSender {

    private final NotifierBotProperties properties;
    private final NotifierCommandProcessor notifierCommandProcessor;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            long chatId = message.getChatId();
            if (message.isCommand()) {
                processCommand(chatId, message.getText());
            } else {
                processText(chatId, message.getText());
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

    @Override
    public void sendMessage(String text) {

    }

    private void processCommand(long chatId, String command) {
        log.info("Command {} from {}", command, chatId);
        notifierCommandProcessor.process(command, chatId, this);
    }

    private void processText(long chatId, String text) {
        log.info("Text {} from {}", text, chatId);
    }
}
