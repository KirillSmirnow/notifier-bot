package org.smirnowku.notifier.bot.processor;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.bot.message.MessageSender;
import org.smirnowku.notifier.dto.subscription.SubscriptionCreate;
import org.smirnowku.notifier.exception.BaseException;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.service.ChannelService;
import org.smirnowku.notifier.service.ChatService;
import org.smirnowku.notifier.service.SubscriptionService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandProcessor {

    private final MessageSender messageSender;
    private final TextProcessor textProcessor;
    private final ChatService chatService;
    private final ChannelService channelService;
    private final SubscriptionService subscriptionService;

    public void process(long chatId, String command) {
        switch (command) {
            case "/start":
                start(chatId);
                break;

            case "/subscribe":
                subscribe(chatId);
                break;
        }
    }

    private void start(long chatId) {
        try {
            chatService.create(new Chat(chatId));
            messageSender.send(chatId, "You have been registered in the service");
        } catch (BaseException e) {
            messageSender.send(chatId, "You are already registered in the service");
        }
    }

    private void subscribe(long chatId) {
        messageSender.send(chatId, "Write the name of the channel you want to subscribe to");
        textProcessor.addListener(chatId, text -> subscribe(chatId, text));
    }

    private void subscribe(long chatId, String channelName) {
        try {
            Chat chat = chatService.getByTelegramId(chatId);
            Channel channel = channelService.getByName(channelName);
            subscriptionService.subscribe(new SubscriptionCreate(chat, channel));
            messageSender.send(chatId, "You have been subscribed to channel " + channel.getName());
        } catch (BaseException e) {
            messageSender.send(chatId, e.getMessage());
        }
    }
}
