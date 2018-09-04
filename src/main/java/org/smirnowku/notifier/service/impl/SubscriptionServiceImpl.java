package org.smirnowku.notifier.service.impl;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.dto.subscription.SubscriptionAsSubscriber;
import org.smirnowku.notifier.dto.subscription.SubscriptionCreate;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Subscription;
import org.smirnowku.notifier.model.User;
import org.smirnowku.notifier.repository.SubscriptionRepository;
import org.smirnowku.notifier.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionAsSubscriber subscribe(SubscriptionCreate subscriptionCreate) {
        User subscriber = subscriptionCreate.getUser();
        Channel channel = subscriptionCreate.getChannel();
        Subscription subscription = new Subscription(subscriber, channel);
        return SubscriptionAsSubscriber.of(subscriptionRepository.save(subscription));
    }

    @Override
    public void unsubscribe(Subscription subscription) {
        subscriptionRepository.delete(subscription);
    }

    @Override
    public Collection<SubscriptionAsSubscriber> getBySubscriber(User subscriber) {
        return subscriptionRepository.findByUser(subscriber).stream()
                .map(SubscriptionAsSubscriber::of)
                .collect(Collectors.toList());
    }
}
