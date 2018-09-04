package org.smirnowku.notifier.service.impl;

import org.smirnowku.notifier.dto.notification.NotificationAsSubscriber;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.service.NotificationListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultNotificationListener implements NotificationListener {

    @Override
    public void onNotificationReceived(Chat chat, NotificationAsSubscriber notification) {
    }
}
