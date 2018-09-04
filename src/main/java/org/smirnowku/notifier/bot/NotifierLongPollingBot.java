package org.smirnowku.notifier.bot;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smirnowku.notifier.bot.processor.CommandProcessor;
import org.smirnowku.notifier.bot.processor.TextProcessor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotifierLongPollingBot extends TelegramLongPollingBot {

    private final NotifierBotProperties properties;

    @Setter
    private CommandProcessor commandProcessor;
    @Setter
    private TextProcessor textProcessor;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            long chatId = message.getChatId();
            if (message.isCommand()) {
                commandProcessor.process(chatId, message.getText());
            } else {
                textProcessor.process(chatId, message.getText());
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
}
