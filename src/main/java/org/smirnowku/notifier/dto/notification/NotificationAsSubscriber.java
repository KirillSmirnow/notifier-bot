package org.smirnowku.notifier.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.smirnowku.notifier.model.Notification;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationAsSubscriber {

    private String message;

    public static NotificationAsSubscriber of(Notification notification) {
        return new NotificationAsSubscriber(notification.getMessage());
    }
}
