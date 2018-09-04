package org.smirnowku.notifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class NotifierApp {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(NotifierApp.class, args);
    }
}
