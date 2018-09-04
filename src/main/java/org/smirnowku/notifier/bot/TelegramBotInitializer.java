package org.smirnowku.notifier.bot;

import lombok.RequiredArgsConstructor;
import org.smirnowku.notifier.bot.message.MessageSender;
import org.smirnowku.notifier.bot.processor.CommandProcessor;
import org.smirnowku.notifier.bot.processor.TextProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@Component
@RequiredArgsConstructor
public class TelegramBotInitializer implements ApplicationRunner {

    private final NotifierLongPollingBot notifierLongPollingBot;
    private final CommandProcessor commandProcessor;
    private final TextProcessor textProcessor;
    private final MessageSender messageSender;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TelegramBotsApi api = new TelegramBotsApi();
        api.registerBot(notifierLongPollingBot);
        notifierLongPollingBot.setCommandProcessor(commandProcessor);
        notifierLongPollingBot.setTextProcessor(textProcessor);
        notifierLongPollingBot.setMessageSender(messageSender);
    }
}
