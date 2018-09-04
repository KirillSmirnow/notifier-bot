package org.smirnowku.notifier.bot.processor;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.bot.message.MessageSender;
import org.smirnowku.notifier.dto.channel.ChannelAsAdmin;
import org.smirnowku.notifier.dto.channel.ChannelAsSubscriber;
import org.smirnowku.notifier.dto.channel.ChannelCreate;
import org.smirnowku.notifier.dto.subscription.SubscriptionAsSubscriber;
import org.smirnowku.notifier.dto.subscription.SubscriptionCreate;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.service.ChannelService;
import org.smirnowku.notifier.service.ChatService;
import org.smirnowku.notifier.service.SubscriptionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommandProcessor {

    private final MessageSender messageSender;
    private final TextProcessor textProcessor;
    private final ChatService chatService;
    private final ChannelService channelService;
    private final SubscriptionService subscriptionService;

    public void process(long chatId, String command) {
        textProcessor.removeListener(chatId);
        switch (command) {
            case "/start":
                start(chatId);
                break;
            case "/list":
                list(chatId);
                break;
            case "/subscribe":
                subscribe(chatId);
                break;
            case "/unsubscribe":
                unsubscribe(chatId);
                break;
            case "/create":
                create(chatId);
                break;
            case "/admin":
                admin(chatId);
                break;
        }
    }

    private void start(long chatId) {
        chatService.create(new Chat(chatId));
        messageSender.send(chatId, "You have been registered in the service");
    }

    private void list(long chatId) {
        Chat chat = chatService.getByTelegramId(chatId);
        List<String> channels = subscriptionService.getBySubscriber(chat).stream()
                .map(SubscriptionAsSubscriber::getChannel)
                .map(ChannelAsSubscriber::getName)
                .collect(Collectors.toList());
        if (channels.isEmpty()) {
            messageSender.send(chatId, "You currently aren't subscribed to any channel");
        } else {
            messageSender.send(chatId, "You are subscribed to:\n * " +
                    String.join("\n * ", channels));
        }
    }

    private void subscribe(long chatId) {
        messageSender.send(chatId, "Write the name of the channel you want to subscribe to");
        textProcessor.addListener(chatId, text -> subscribe(chatId, text));
    }

    private void subscribe(long chatId, String channelName) {
        Chat chat = chatService.getByTelegramId(chatId);
        Channel channel = channelService.getByName(channelName);
        subscriptionService.subscribe(new SubscriptionCreate(chat, channel));
        messageSender.send(chatId, "You have subscribed to channel " + channel.getName());
    }

    private void unsubscribe(long chatId) {
        messageSender.send(chatId, "Write the name of the channel you want to unsubscribe from");
        textProcessor.addListener(chatId, text -> unsubscribe(chatId, text));
    }

    private void unsubscribe(long chatId, String channelName) {
        Chat chat = chatService.getByTelegramId(chatId);
        Channel channel = channelService.getByName(channelName);
        subscriptionService.unsubscribe(chat, channel);
        messageSender.send(chatId, "You have unsubscribed from channel " + channel.getName());
    }

    private void create(long chatId) {
        messageSender.send(chatId, "Write the name of the channel you want to create");
        textProcessor.addListener(chatId, text -> create(chatId, text));
    }

    private void create(long chatId, String channelName) {
        Chat chat = chatService.getByTelegramId(chatId);
        ChannelAsAdmin channel = channelService.create(new ChannelCreate(channelName, chat, false));
        messageSender.send(chatId, "You have successfully created channel\n" + formatChannel(channel));
    }

    private void admin(long chatId) {
        Chat chat = chatService.getByTelegramId(chatId);
        List<String> channels = channelService.getByAdmin(chat).stream()
                .map(this::formatChannel)
                .collect(Collectors.toList());
        if (channels.isEmpty()) {
            messageSender.send(chatId, "You haven't created any channel yet");
        } else {
            messageSender.send(chatId, "Your channels:\n\n" + String.join("\n\n", channels));
        }
    }

    private String formatChannel(ChannelAsAdmin channel) {
        return String.format("%s\ntoken: %s\nsubscription code: %s", channel.getName(),
                channel.getToken(), channel.getSubscriptionCode() == null ? "<public>" : channel.getSubscriptionCode());
    }
}
