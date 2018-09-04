package org.smirnowku.notifier.repository;

import org.smirnowku.notifier.model.Subscription;
import org.smirnowku.notifier.model.User;

import java.util.Collection;

public interface SubscriptionRepository extends BaseRepository<Subscription> {

    Collection<Subscription> findByUser(User subscriber);
}
