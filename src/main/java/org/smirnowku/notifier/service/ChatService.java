package org.smirnowku.notifier.service;

import org.smirnowku.notifier.model.Chat;

public interface ChatService {

    Chat create(Chat chat);

    Chat getByTelegramId(int telegramId);
}
