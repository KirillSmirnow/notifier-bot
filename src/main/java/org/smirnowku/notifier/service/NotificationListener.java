package org.smirnowku.notifier.service;

import org.smirnowku.notifier.dto.notification.NotificationAsSubscriber;
import org.smirnowku.notifier.model.User;

public interface NotificationListener {

    void onNotificationReceived(User user, NotificationAsSubscriber notification);
}
