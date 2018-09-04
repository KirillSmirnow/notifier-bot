package org.smirnowku.notifier.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.smirnowku.notifier.dto.notification.NotificationAsSubscriber;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.service.NotificationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingNotificationListener implements NotificationListener {

    @Override
    public void onNotificationReceived(Chat chat, NotificationAsSubscriber notification) {
        log.info("Notifying {}: {}", chat, notification.getMessage());
    }
}
