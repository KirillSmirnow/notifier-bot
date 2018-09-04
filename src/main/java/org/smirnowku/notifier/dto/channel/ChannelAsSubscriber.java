package org.smirnowku.notifier.dto.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.smirnowku.notifier.model.Channel;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelAsSubscriber {

    private String name;

    public static ChannelAsSubscriber of(Channel channel) {
        return new ChannelAsSubscriber(channel.getName());
    }
}
