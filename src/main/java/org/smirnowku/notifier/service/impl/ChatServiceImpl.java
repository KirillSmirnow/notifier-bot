package org.smirnowku.notifier.service.impl;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.exception.ConflictException;
import org.smirnowku.notifier.exception.NotFoundException;
import org.smirnowku.notifier.model.Chat;
import org.smirnowku.notifier.repository.ChatRepository;
import org.smirnowku.notifier.service.ChatService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public Chat create(Chat chat) {
        if (chatRepository.findByTelegramId(chat.getTelegramId()).isPresent()) {
            throw new ConflictException("You are already registered in the service");
        }
        return chatRepository.save(chat);
    }

    @Override
    public Chat getByTelegramId(long telegramId) {
        return chatRepository.findByTelegramId(telegramId)
                .orElseThrow(() -> new NotFoundException("Chat %d not found", telegramId));
    }
}
