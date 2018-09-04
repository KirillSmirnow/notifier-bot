package org.smirnowku.notifier.bot.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smirnowku.notifier.bot.NotifierLongPollingBot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSenderImpl implements MessageSender {

    private final NotifierLongPollingBot bot;

    @Override
    public void send(long chatId, String text) {
        try {
            bot.execute(new SendMessage(chatId, text));
        } catch (Exception e) {
            log.warn("Error sending message: {}", e.getMessage());
        }
    }
}
