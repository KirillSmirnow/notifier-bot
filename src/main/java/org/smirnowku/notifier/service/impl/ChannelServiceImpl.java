package org.smirnowku.notifier.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.smirnowku.notifier.dto.channel.ChannelAsAdmin;
import org.smirnowku.notifier.dto.channel.ChannelCreate;
import org.smirnowku.notifier.exception.ConflictException;
import org.smirnowku.notifier.exception.NotFoundException;
import org.smirnowku.notifier.model.Channel;
import org.smirnowku.notifier.model.User;
import org.smirnowku.notifier.repository.ChannelRepository;
import org.smirnowku.notifier.service.ChannelService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;

    @Override
    public ChannelAsAdmin create(ChannelCreate channelCreate) {
        Channel channel = channelCreate.isRestricted() ? createPrivate(channelCreate) : createPublic(channelCreate);
        if (channelRepository.findByName(channelCreate.getName()).isPresent()) {
            throw new ConflictException("Channel with such name already exists");
        }
        return ChannelAsAdmin.of(channelRepository.save(channel));
    }

    @Override
    public Collection<ChannelAsAdmin> getByAdmin(User admin) {
        return channelRepository.findByAdmin(admin).stream()
                .map(ChannelAsAdmin::of)
                .collect(Collectors.toList());
    }

    @Override
    public Channel getByName(String name) {
        return channelRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Channel '%s' not found", name));
    }

    @Override
    public Channel getByToken(String token) {
        return channelRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Channel not found"));
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
