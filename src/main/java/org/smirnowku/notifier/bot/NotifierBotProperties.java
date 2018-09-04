package org.smirnowku.notifier.bot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "notifier")
public class NotifierBotProperties {

    private String botUsername;
    private String botToken;
}
