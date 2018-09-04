package org.smirnowku.notifier.service;

import org.smirnowku.notifier.dto.ChannelCreate;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.User;

import java.util.Collection;

public interface ChannelService {

    Channel create(ChannelCreate channelCreate);

    Channel getByName(String name);

    Channel getByToken(String token);

    Collection<Channel> getByAdmin(User admin);
}
