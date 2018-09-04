package org.smirnowku.notifier.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.smirnowku.notifier.model.Channel;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreate {

    private String message;
    private Channel channel;
}
