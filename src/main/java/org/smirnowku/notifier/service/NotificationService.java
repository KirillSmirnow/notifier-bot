package org.smirnowku.notifier.service;

import org.smirnowku.notifier.dto.notification.NotificationCreate;
import org.smirnowku.notifier.model.Notification;

public interface NotificationService {

    Notification create(NotificationCreate notificationCreate);
}
