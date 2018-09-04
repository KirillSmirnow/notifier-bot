package org.smirnowku.notifier.dto.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelAsAdmin {

    private String name;
    private String token;
    private String subscriptionCode;
    private Chat admin;

    public static ChannelAsAdmin of(Channel channel) {
        return new ChannelAsAdmin(channel.getName(), channel.getToken(),
                channel.getSubscriptionCode(), channel.getAdmin());
    }
}
