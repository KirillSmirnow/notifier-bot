package org.smirnowku.notifier.service;

import org.smirnowku.notifier.dto.subscription.SubscriptionAsSubscriber;
import org.smirnowku.notifier.dto.subscription.SubscriptionCreate;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;

import java.util.Collection;

public interface SubscriptionService {

    SubscriptionAsSubscriber subscribe(SubscriptionCreate subscriptionCreate);

    void unsubscribe(Chat chat, Channel channel);

    Collection<SubscriptionAsSubscriber> getBySubscriber(Chat subscriber);
}
