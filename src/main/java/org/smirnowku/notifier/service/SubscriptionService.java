package org.smirnowku.notifier.service;

import org.smirnowku.notifier.dto.subscription.SubscriptionAsSubscriber;
import org.smirnowku.notifier.dto.subscription.SubscriptionCreate;
import org.smirnowku.notifier.model.Subscription;
import org.smirnowku.notifier.model.User;

import java.util.Collection;

public interface SubscriptionService {

    SubscriptionAsSubscriber subscribe(SubscriptionCreate subscriptionCreate);

    void unsubscribe(Subscription subscription);

    Collection<SubscriptionAsSubscriber> getBySubscriber(User subscriber);
}
