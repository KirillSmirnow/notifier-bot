package org.smirnowku.notifier.service;

import org.smirnowku.notifier.dto.channel.ChannelAsAdmin;
import org.smirnowku.notifier.dto.channel.ChannelCreate;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.Chat;

import java.util.Collection;

public interface ChannelService {

    ChannelAsAdmin create(ChannelCreate channelCreate);

    Collection<ChannelAsAdmin> getByAdmin(Chat admin);

    Channel getByName(String name);

    Channel getByToken(String token);
}
