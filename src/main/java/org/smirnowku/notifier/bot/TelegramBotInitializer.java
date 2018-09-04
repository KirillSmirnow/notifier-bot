package org.smirnowku.notifier.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@Component
@RequiredArgsConstructor
public class TelegramBotInitializer implements ApplicationRunner {

    private final NotifierLongPollingBot notifierLongPollingBot;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        TelegramBotsApi api = new TelegramBotsApi();
        api.registerBot(notifierLongPollingBot);
    }
}
