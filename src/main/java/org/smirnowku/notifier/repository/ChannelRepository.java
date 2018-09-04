package org.smirnowku.notifier.repository;

import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;

import java.util.Collection;
import java.util.Optional;

public interface ChannelRepository extends BaseRepository<Channel> {

    Optional<Channel> findByName(String name);

    Optional<Channel> findByToken(String token);

    Collection<Channel> findByAdmin(Chat admin);
}
