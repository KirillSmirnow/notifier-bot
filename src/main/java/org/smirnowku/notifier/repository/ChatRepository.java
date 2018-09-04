package org.smirnowku.notifier.repository;

import org.smirnowku.notifier.model.Chat;

import java.util.Optional;

public interface ChatRepository extends BaseRepository<Chat> {

    Optional<Chat> findByTelegramId(long id);
}
