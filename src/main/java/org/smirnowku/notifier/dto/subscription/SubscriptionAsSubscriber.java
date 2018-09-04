package org.smirnowku.notifier.dto.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.smirnowku.notifier.dto.channel.ChannelAsSubscriber;
import org.smirnowku.notifier.model.Subscription;
import org.smirnowku.notifier.model.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionAsSubscriber {

    private User user;
    private ChannelAsSubscriber channel;

    public static SubscriptionAsSubscriber of(Subscription subscription) {
        ChannelAsSubscriber channelAsSubscriber = ChannelAsSubscriber.of(subscription.getChannel());
        return new SubscriptionAsSubscriber(subscription.getUser(), channelAsSubscriber);
    }
}
