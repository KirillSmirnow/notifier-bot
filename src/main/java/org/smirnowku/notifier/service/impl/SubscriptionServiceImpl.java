package org.smirnowku.notifier.service.impl;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.dto.subscription.SubscriptionAsSubscriber;
import org.smirnowku.notifier.dto.subscription.SubscriptionCreate;
import org.smirnowku.notifier.exception.ConflictException;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.model.Subscription;
import org.smirnowku.notifier.repository.SubscriptionRepository;
import org.smirnowku.notifier.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionAsSubscriber subscribe(SubscriptionCreate subscriptionCreate) {
        Chat subscriber = subscriptionCreate.getChat();
        Channel channel = subscriptionCreate.getChannel();
        if (isSubscribed(subscriber, channel)) {
            throw new ConflictException("You are already subscribed to this channel");
        }
        Subscription subscription = new Subscription(subscriber, channel);
        return SubscriptionAsSubscriber.of(subscriptionRepository.save(subscription));
    }

    @Override
    public void unsubscribe(Chat chat, Channel channel) {
        Optional<Subscription> subscription = subscriptionRepository.findByChannelAndChat(channel, chat);
        if (subscription.isPresent()) {
            subscriptionRepository.delete(subscription.get());
        } else {
            throw new ConflictException("You are not subscribed to this channel");
        }
    }

    @Override
    public Collection<SubscriptionAsSubscriber> getBySubscriber(Chat subscriber) {
        return subscriptionRepository.findByChat(subscriber).stream()
                .map(SubscriptionAsSubscriber::of)
                .collect(Collectors.toList());
    }

    private boolean isSubscribed(Chat chat, Channel channel) {
        return subscriptionRepository.existsByChannelAndChat(channel, chat);
    }
}
