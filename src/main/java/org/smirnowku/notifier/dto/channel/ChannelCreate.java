package org.smirnowku.notifier.dto.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.smirnowku.notifier.model.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelCreate {

    private String name;
    private User admin;
    private boolean restricted;
}
