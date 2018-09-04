package org.smirnowku.notifier.bot.processor;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.bot.message.MessageSender;
import org.smirnowku.notifier.service.SubscriptionService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TextProcessor {

    private final MessageSender messageSender;
    private final SubscriptionService subscriptionService;

    public void process(long chatId, String text) {
    }
}
