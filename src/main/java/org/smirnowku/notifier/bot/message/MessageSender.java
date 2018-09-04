package org.smirnowku.notifier.bot.message;

public interface MessageSender {

    void send(long chatId, String text);
}
