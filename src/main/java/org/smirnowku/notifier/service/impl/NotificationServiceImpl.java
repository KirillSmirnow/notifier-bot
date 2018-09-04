package org.smirnowku.notifier.service.impl;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.dto.notification.NotificationCreate;
import org.smirnowku.notifier.model.Notification;
import org.smirnowku.notifier.repository.NotificationRepository;
import org.smirnowku.notifier.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification create(NotificationCreate notificationCreate) {
        Notification notification = new Notification(notificationCreate.getChannel(), notificationCreate.getMessage());
        return notificationRepository.save(notification);
    }
}
