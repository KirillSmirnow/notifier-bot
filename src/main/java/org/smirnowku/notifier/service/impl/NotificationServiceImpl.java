package org.smirnowku.notifier.service.impl;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.dto.notification.NotificationAsSubscriber;
import org.smirnowku.notifier.dto.notification.NotificationCreate;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.model.Notification;
import org.smirnowku.notifier.model.Subscription;
import org.smirnowku.notifier.repository.NotificationRepository;
import org.smirnowku.notifier.repository.SubscriptionRepository;
import org.smirnowku.notifier.service.NotificationListener;
import org.smirnowku.notifier.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final Collection<NotificationListener> notificationListeners;

    @Override
    public Notification create(NotificationCreate notificationCreate) {
        Notification notification = new Notification(notificationCreate.getChannel(), notificationCreate.getMessage());
        notify(notification);
        return notificationRepository.save(notification);
    }

    private void notify(Notification notification) {
        Channel channel = notification.getChannel();
        Collection<Chat> subscribers = subscriptionRepository.findByChannel(channel)
                .parallelStream()
                .map(Subscription::getChat)
                .collect(Collectors.toList());
        notify(subscribers, notification);
    }

    private void notify(Collection<Chat> chats, Notification notification) {
        chats.parallelStream().forEach(user -> notify(user, notification));
    }

    private void notify(Chat chat, Notification notification) {
        notificationListeners.parallelStream().forEach(listener ->
                listener.onNotificationReceived(chat, NotificationAsSubscriber.of(notification)));
    }
}
