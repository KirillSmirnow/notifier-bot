package org.smirnowku.notifier.bot;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.bot.message.MessageSender;
import org.smirnowku.notifier.dto.notification.NotificationAsSubscriber;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.service.NotificationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramBotNotificationListener implements NotificationListener {

    private final MessageSender messageSender;

    @Override
    public void onNotificationReceived(Chat chat, NotificationAsSubscriber notification) {
        messageSender.send(chat.getTelegramId(), notification.getMessage());
    }
}
