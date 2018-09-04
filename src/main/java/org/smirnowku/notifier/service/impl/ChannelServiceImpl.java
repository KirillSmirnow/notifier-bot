package org.smirnowku.notifier.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.smirnowku.notifier.dto.ChannelCreate;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.User;
import org.smirnowku.notifier.repository.ChannelRepository;
import org.smirnowku.notifier.service.ChannelService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    @Override
    public Channel create(ChannelCreate channelCreate) {
        Channel channel = channelCreate.isRestricted() ? createPrivate(channelCreate) : createPublic(channelCreate);
        return channelRepository.save(channel);
    }

    @Override
    public Channel getByName(String name) {
        return null;
    }

    @Override
    public Channel getByToken(String token) {
        return null;
    }

    @Override
    public Collection<Channel> getByAdmin(User admin) {
        return null;
    }

    private Channel createPublic(ChannelCreate channelCreate) {
        String token = RandomStringUtils.randomAlphabetic(50);
        return Channel.createPublic(channelCreate.getAdmin(), channelCreate.getName(), token);
    }

    private Channel createPrivate(ChannelCreate channelCreate) {
        String token = RandomStringUtils.randomAlphabetic(50);
        String subscriptionCode = RandomStringUtils.randomAlphabetic(50);
        return Channel.createPrivate(channelCreate.getAdmin(), channelCreate.getName(), token, subscriptionCode);
    }
}
