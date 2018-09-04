package org.smirnowku.notifier.dto.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionCreate {

    private Chat chat;
    private Channel channel;
}
