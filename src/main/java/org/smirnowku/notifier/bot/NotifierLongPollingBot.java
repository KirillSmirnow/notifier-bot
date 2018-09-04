package org.smirnowku.notifier.bot;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smirnowku.notifier.bot.message.MessageSender;
import org.smirnowku.notifier.bot.processor.CommandProcessor;
import org.smirnowku.notifier.bot.processor.TextProcessor;
import org.smirnowku.notifier.exception.BaseException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Setter
@RequiredArgsConstructor
public class NotifierLongPollingBot extends TelegramLongPollingBot {

    private final NotifierBotProperties properties;

    private MessageSender messageSender;
    private CommandProcessor commandProcessor;
    private TextProcessor textProcessor;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            long chatId = message.getChatId();
            try {
                if (message.isCommand()) {
                    commandProcessor.process(chatId, message.getText());
                } else {
                    textProcessor.process(chatId, message.getText());
                }
            } catch (BaseException e) {
                messageSender.send(chatId, e.getMessage());
            } catch (ConstraintViolationException e) {
                List<String> errors = e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
                messageSender.send(chatId, String.join("\n", errors));
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
