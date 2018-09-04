package org.smirnowku.notifier.repository;

import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.model.Subscription;

import java.util.Collection;

public interface SubscriptionRepository extends BaseRepository<Subscription> {

    boolean existsByChannelAndChat(Channel channel, Chat chat);

    Collection<Subscription> findByChat(Chat subscriber);

    Collection<Subscription> findByChannel(Channel channel);
}
