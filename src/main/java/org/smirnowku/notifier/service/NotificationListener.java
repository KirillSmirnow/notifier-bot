package org.smirnowku.notifier.service;

import org.smirnowku.notifier.dto.notification.NotificationAsSubscriber;
import org.smirnowku.notifier.model.Chat;

public interface NotificationListener {

    void onNotificationReceived(Chat chat, NotificationAsSubscriber notification);
}
