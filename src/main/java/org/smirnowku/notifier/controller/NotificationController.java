package org.smirnowku.notifier.controller;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.dto.notification.NotificationCreate;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Notification;
import org.smirnowku.notifier.service.ChannelService;
import org.smirnowku.notifier.service.NotificationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/channels/{token}")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final ChannelService channelService;

    @PostMapping("/notify/{message}")
    public Notification create(@PathVariable String token, @PathVariable String message) {
        Channel channel = channelService.getByToken(token);
        return notificationService.create(new NotificationCreate(message, channel));
    }
}
